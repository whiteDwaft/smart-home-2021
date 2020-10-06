package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.handlers.DoorEventHandler;
import ru.sbt.mipt.oop.handlers.LightEventHandler;
import ru.sbt.mipt.oop.input.FileReader;
import ru.sbt.mipt.oop.input.HomeLoader;


import java.io.IOException;
import java.util.Arrays;

public class Application {

    private final HomeLoader homeLoader;
    private final SmartHome smartHome;
    private final Simulating simulating;


    public Application(HomeLoader homeLoader,Simulating simulating) throws IOException {
        this.homeLoader = homeLoader;
        smartHome = this.homeLoader.loadFromFile();
        this.simulating = simulating;
    }
    public static void main(String... args) throws IOException {
        Application application = new Application(new FileReader("smart-home-1.js"),
                new Simulating(new DummyRandomEventGenerator(), Arrays.asList(new DoorEventHandler(),new LightEventHandler(),new HallDoorEventHandler())));

        application.run();
    }
    public void run(){
        simulating.simulating(smartHome);
    }
}
