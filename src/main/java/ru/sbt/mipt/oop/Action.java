package ru.sbt.mipt.oop;

public interface Action {
    void executeOne(SensorEvent sensorEvent);
    void executeAll(SensorEvent sensorEvent);
}
