### CQRS в Instagram

#####1. Скролинг ленты

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
        PostDto post = postService.getPost(userId,postId);
        Map<Object, Object> model = new HashMap<>();
        model.put("text", post.getText );
        model.put("picture", post.getPic);
        return ok(model);
    }
}
```
 + DTO
```java
@Data
public class PostDto {
    Long id;
    Long userId ;
    String text;
    String pictPath;
    Long likes;
}
```
#####2. Публикация поста

Обратная ситуация от получения поста(будет менятся состояние)
 + Сервис
 ```java
 public interface IPostCommandService {
  
     void setPost(Long userId,
                      String text,
                      String pictPath);
    
}
```
 + Контроллер
 ```java
@RestController
public class PostCommandRestController {
 
    @Autowired
    private IPostCommandService postService;
    
    @PostMapping(value = "/getPost")
    public ResponseEntity setPost(@RequestBody PostDto data) {
        postService.setPost(data.getUserId(), data.getText, data.getPict);
        return new ResponseEntity(HttpStatus.OK);
    }
}
``` 