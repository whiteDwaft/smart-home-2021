package ru.sbt.mipt.oop.handlers;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.elements.Room;
import ru.sbt.mipt.oop.input.FileReader;
import ru.sbt.mipt.oop.input.HomeLoader;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;

import static org.junit.jupiter.api.Assertions.*;

class HallDoorEventHandlerTest {

//    @Test
//    void handle() {
//        HomeLoader homeLoader = new FileReader("smart-home-1.js");
//        SmartHome smartHome = homeLoader.loadFromFile();
//        EventHandler hallDoorEventHandler = new HallDoorEventHandler();
//        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "" + 4);// 4 - objectID of HallDoor
//        hallDoorEventHandler.handle(event, smartHome);
//
//        for (Room room : smartHome.getRooms()) {
//            boolean res = room.getLights().stream().noneMatch(it -> it.isOn());
//            assertTrue(res, "all lights must be switched on");
//        }
//    }
}