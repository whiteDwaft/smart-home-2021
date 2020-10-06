package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

public interface EventHandler {
    void handle(SensorEvent sensorEvent, SmartHome smartHome);

}
