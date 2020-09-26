package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

public class HomeBuilder {
    private SmartHome generateSmartHomeObject(){
        Room kitchen = new Room(Arrays.asList(new Light("1", false), new Light("2", true)),
                Collections.singletonList(new Door(false, "1")),
                "kitchen");
        Room bathroom = new Room(Arrays.asList(new Light("3", true)),
                Collections.singletonList(new Door(false, "2")),
                "bathroom");
        Room bedroom = new Room(Arrays.asList(new Light("4", false), new Light("5", false), new Light("6", false)),
                Collections.singletonList(new Door(true, "3")),
                "bedroom");
        Room hall = new Room(Arrays.asList(new Light("7", false), new Light("8", false), new Light("9", false)),
                Collections.singletonList(new Door(false, "4")),
                "hall");
        return new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));
    }
    public static void main(String[] args) throws IOException {
        HomeBuilder homeBuilder = new HomeBuilder();
        FileWriter fileWriter = new FileWriter("smart-home-1.js");

        SmartHome smartHome = homeBuilder.generateSmartHomeObject();
        fileWriter.saveObjectInJSONFile(smartHome);

    }

}
