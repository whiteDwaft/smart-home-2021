package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.handlers.EventHandler;

import java.util.List;

import static ru.sbt.mipt.oop.SensorEventType.*;

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
