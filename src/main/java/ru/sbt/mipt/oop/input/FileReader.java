package ru.sbt.mipt.oop.input;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader implements HomeLoader {
    private final String filename;

    public FileReader(String filename) {
        this.filename = filename;
    }

    @Override
    public SmartHome loadFromFile() {
        Gson gson = new Gson();
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            new RuntimeException("could't read from File",e);
        }
        return gson.fromJson(json, SmartHome.class);
    }
}
