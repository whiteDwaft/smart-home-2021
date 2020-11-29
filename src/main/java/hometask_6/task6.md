### CQRS в Instagram

##### 1. Скролинг ленты

Естественно, при скролинге ленты она не подгружается вся,
 посты подгружаются при видимости их пользователем.
 
 Совмещю Spring (Spring MVC) вместе с SQRS
 
 Будет описан только Query составляющая(состояние не меняется):
 + Сервис
 ```java
 public interface IPostQueryService {
  
     Post getPost(int userId, int postId);
    
}
```
 + Контроллер
```java
@RestController
public class PostQueryRestController {
 
    @Autowired
    private IPostQueryService postService;
    
    @GetMapping(value = "/getPost")
    public ResponseEntity getPost(int userId, int postId) {
        PostMainDto post = postService.getPost(userId,postId);
        Map<Object, Object> model = new HashMap<>();
        model.put("text", post.getText );
        model.put("picture", post.getPic);
        return ok(model);
    }
}
```
 + DTO
```java
@Data //Lombok anotation
public class PostMainDto {
    Long id;
    Long userId ;
    String text;
    String pictPath;
}
```

Поскольку скролинг ленты - самое частое действие пользователся,
то данные должны поступать поступать максимально быстро.Следовательно
разбить БД на портиции и раскидать по машинкам. и при все запросы к БД,
связанные с постами будут вычисляться распределенно (ну то, что еще 
будет организована репликация портиций это и так понятно). Ну и 
обработкой подобных запросов должны заниматься сразу несколько машинок
(сделать аналог пула потоков, только с машинками)
##### 2. Публикация поста

Обратная ситуация от получения поста(будет менятся состояние)
 + Сервис
 ```java
 public interface IPostCommandService {
  
     void setOrCreatePost(PostMainDto data);
    
}
```
 + Контроллер
 ```java
@RestController
public class PostCommandRestController {
 
    @Autowired
    private IPostCommandService postService;
    
    @PostMapping(value = "/setPost")
    public ResponseEntity setPost(@RequestBody PostMainDto data) {
        postService.setOrCreatePost(data);
        return new ResponseEntity(HttpStatus.OK);
    }
}
```
##### 3. Проставление лайков

Поставить лайк = изменить состояние поста

 + Сервис
 
 Добавим в сервис новый метод (перегруженный):
 ```java
public interface IPostCommandService {

    //setOrCreatePost method here

     void setOrCreatePost(PostLikesDto data);
}
```
 + Контроллер
 
 Добавим в контроллер новый метод:
 ```java
@RestController
public class PostCommandRestController {
 
    @Autowired
    private IPostCommandService postService;
    
    //  setPost method here

    @PostMapping(value = "/like")
    public ResponseEntity like(@RequestBody PostLikesDto data) {
        data.setLikes(data.getLikes + 1);
        postService.setPost(data);
        return new ResponseEntity(HttpStatus.OK);
    }
}
```
 + DTO
 
 Возможно создавать почти одинаковые DTO'шки это дублирование кода,
 но при одной DTO про посты придется делать "костыли" c подтягиванием
 лишних данных(не нужных в определенный момент) 
 ```java
@Data //Lombok anotation
public class PostLikesDto {
    Long id;
    Long userId ;
    Long likes;
}
```
 Сам лайк - действие не такое популярное и "есть не просит" 
 (результат запроса ```/like``` не жизненно важен), поэтому
 сильно заморачиваться о поиске поста для лайка не стоит, хотя
 распределенный поиск уже был организован в скроллинге(ну можно
  обмануть систему ("закешировать" лайк) и спокойно обновить БД) 
  
##### 4. Комментарий

Написать комментарий - примерно тоже самое, что и поставить лайк,
__но__ комментарий пишется кому-то => результат кому-то должен прийти
в кратчайшие строки( но не так быстро, как скролинг ленты).

 + Сервис
 
Так-же создатся новый метод в сервисе:
 ```java
public interface IPostCommandService {

    //other method here

     void setOrCreatePost(PostCommentsDto data);
}
```

 + Контроллер
 
 Добавим в контроллер новый метод:
  ```java
 @RestController
 public class PostCommandRestController {
  
     @Autowired
     private IPostCommandService postService;
     
     //  setPost method here
 
     @PostMapping(value = "/comment")
     public ResponseEntity comment(@RequestBody PostCommentsDto data) {
         postService.setPost(data);
         return new ResponseEntity(HttpStatus.OK);
     }
 }
 ```

+ DTO
 
 Возможно возник вопрос, зачем тащить везде за собой и id(поста) и
 userId, вместо одного id... У меня просто есть чуйка, что соцсеть
 использует составные ключи для идентификации поста, т.к. 1 млрд 
 пользователей * 100+ постов у каждого = слишком большой id'шник (
 хотя это детская проблема - в java для этого есть BigInteger,
 или можно все превращать в строку) 
  
 ```java
@Data //Lombok anotation
public class PostCommentsDto {
    Long id;
    Long userId ;
    String comment;
}
```
Как я говорил ранее, написание комментария - действие редкое (даже
более редкое, чем поставить лайк), но требует результата, поэтому
информацию по комментам нужно оперативно обновлять (распределенный доступ
к партициям бд), а с запросом на фиксацию написанного комментария может
стправиться одна или несколко нашин (не надо так много машин, как 
при скролинге ленты)
