package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoorTest {

    @Test
    void isOpen() {
        Door door = new Door(false,"" +1);
        boolean res = door.isOpen();
        assertFalse(res,"door must not be open");
    }

    @Test
    void setOpen() {
        Door door = new Door(true,"" +1);
        door.setOpen(false);
        boolean res = door.isOpen();
        assertFalse(res,"door must not be open");
    }

    @Test
    void execute() {
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "" + 1);
        Door door = new Door(false,"" +1);
        door.execute(event);
        boolean res = door.isOpen();
        assertTrue(res,"door must be open");
    }
}