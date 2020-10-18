package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.handlers.EventHandler;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventGenerator;

import java.util.List;

public class LoopEventGenerator {
    private final SensorEventGenerator sensorEventGenerator;
    List<EventHandler> eventHandlers;


    public LoopEventGenerator(SensorEventGenerator sensorEventGenerator, List<EventHandler> eventHandlers) {
        this.sensorEventGenerator = sensorEventGenerator;
        this.eventHandlers = eventHandlers;
    }

    public void simulating(SmartHome smartHome){
        SensorEvent event = sensorEventGenerator.getNextSensorEvent();
        while (event != null){
            SensorEvent finalEvent = event;
            eventHandlers.forEach(it -> it.handle(finalEvent,smartHome));
            event = sensorEventGenerator.getNextSensorEvent();
        }
    }
}
