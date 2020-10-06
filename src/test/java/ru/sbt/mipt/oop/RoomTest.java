package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void execute_testEventWithLights() {
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, "" + 1);
        List<Light> lights = new ArrayList<>();
        lights.add(new Light(""+1,false));
        Room room = new Room(lights,new ArrayList<>(),"room");
        room.execute(event);
        boolean res = lights.get(0).isOn();
        assertTrue(res,"light must be switch on");
    }
    @Test
    void execute_testEventWithDoors() {
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "" + 1);
        List<Door> doors = new ArrayList<>();
        doors.add(new Door(false,""+1));
        Room room = new Room(new ArrayList<>(),doors,"room");
        room.execute(event);
        boolean res = doors.get(0).isOpen();
        assertTrue(res,"light must be switch on");
    }
}