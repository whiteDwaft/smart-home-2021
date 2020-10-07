package ru.sbt.mipt.oop.handlers;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.input.FileReader;
import ru.sbt.mipt.oop.input.HomeLoader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DoorEventHandlerTest {

    @Test
    void handle() throws IOException {
        HomeLoader homeLoader = new FileReader("smart-home-1.js");
        SmartHome smartHome = homeLoader.loadFromFile();
        EventHandler doorEventHandler = new DoorEventHandler();
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "" + 1);
        doorEventHandler.handle(event,smartHome);

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    assertTrue(door.isOpen(), "it must be open");
                }
            }
        }
    }
}