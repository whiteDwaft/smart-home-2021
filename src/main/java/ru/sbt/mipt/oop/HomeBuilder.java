package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.Signalization;
import ru.sbt.mipt.oop.output.FileWriter;
import ru.sbt.mipt.oop.output.HomeUnloader;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class HomeBuilder {
    public final HomeUnloader homeUnloader;

    public HomeBuilder(HomeUnloader homeUnloader) {
        this.homeUnloader = homeUnloader;
    }

    public SmartHome generateSmartHomeObject(){
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
        return new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall),123);
    }
    public static void main(String[] args) throws IOException {
        HomeUnloader homeUnloader = new FileWriter("smart-home-1.js");
        HomeBuilder homeBuilder = new HomeBuilder(homeUnloader);
        SmartHome smartHome = homeBuilder.generateSmartHomeObject();
        homeUnloader.unloadHome(smartHome);

    }

}
