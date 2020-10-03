package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.SensorEvent;

public interface EventHandler {
    void handle(SensorEvent sensorEvent);

}
