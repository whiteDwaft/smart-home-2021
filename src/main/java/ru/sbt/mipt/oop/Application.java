package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.handlers.DoorEventHandler;
import ru.sbt.mipt.oop.handlers.HallDoorEventHandler;
import ru.sbt.mipt.oop.handlers.LightEventHandler;
import ru.sbt.mipt.oop.handlers.SignalizationEventHandler;
import ru.sbt.mipt.oop.input.FileReader;
import ru.sbt.mipt.oop.input.HomeLoader;


import java.io.IOException;
import java.util.Arrays;

public class Application {

    private final HomeLoader homeLoader;
    private SmartHome smartHome;
    private final LoopEventGenerator simulating;


    public Application(HomeLoader homeLoader, LoopEventGenerator simulating) {
        this.homeLoader = homeLoader;
        this.simulating = simulating;
    }

    public static void main(String... args) throws IOException {
        Application application = new Application(new FileReader("smart-home-1.js"),
                new LoopEventGenerator(new DummyRandomEventGenerator(), Arrays.asList(new DoorEventHandler(), new LightEventHandler(), new HallDoorEventHandler())));
        application.smartHome = application.homeLoader.loadFromFile();
        application.run();
    }

    public void run() {
        simulating.simulating(smartHome);
    }
}
