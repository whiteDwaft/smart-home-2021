package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LightTest {

    @Test
    void isOn() {
        Light light = new Light("" + 1,false);
        boolean res = light.isOn();
        assertFalse(res,"light must be switched off");
    }

    @Test
    void setOn() {
        Light light = new Light("" + 1,false);
        light.setOn(true);
        boolean res = light.isOn();
        assertTrue(res,"light must be switched on");
    }

    @Test
    void execute() {
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, "" + 1);
        Light light = new Light("" + 1,false);
        light.execute(event);
        boolean res = light.isOn();
        assertTrue(res,"light must be switched on");
    }
}