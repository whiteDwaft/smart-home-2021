package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
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
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        SensorEventsManager sensorEventsManager = context.getBean(SensorEventsManager.class);
        sensorEventsManager.start();
    }
}
