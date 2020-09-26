package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {
    private final String filename;

    public FileReader(String filename) {
        this.filename = filename;
    }
    //__________Комментарий от студента_________________
    //при смене Class<SmartHome> на Class<Object> возникает ошибка компиляции и я даже не знаю почему
    //все же наследуется от Object...
    SmartHome readJSONFileForGeneratingSmartHome(Class<SmartHome> obj) throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(filename)));
        return gson.fromJson(json, (Type) obj);
    }
}
