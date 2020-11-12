package ru.sbt.mipt.oop.api;

import ru.sbt.mipt.oop.sensor.SensorEventType;

import java.util.Map;

public class Conventor {
    private final Map<String, SensorEventType> conventor;

    public Conventor(Map<String, SensorEventType> conventor) {
        this.conventor = conventor;
    }
    public SensorEventType convertFromEventType(String eventType){
        return conventor.get(eventType);
    }
}
