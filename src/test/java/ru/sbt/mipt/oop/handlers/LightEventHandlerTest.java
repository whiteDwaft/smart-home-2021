package ru.sbt.mipt.oop.handlers;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.input.FileReader;
import ru.sbt.mipt.oop.input.HomeLoader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LightEventHandlerTest {

    @Test
    void handle() throws IOException {
        HomeLoader homeLoader = new FileReader("smart-home-1.js");
        SmartHome smartHome = homeLoader.loadFromFile();
        EventHandler lightEventHandler = new LightEventHandler();
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, "" + 1);
        lightEventHandler.handle(event,smartHome);

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    assertTrue(light.isOn(), "it must be open");
                }
            }
        }
    }
}