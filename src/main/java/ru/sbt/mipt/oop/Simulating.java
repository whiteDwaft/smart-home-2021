package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.handlers.DecorateEventHandler;
import ru.sbt.mipt.oop.handlers.EventHandler;

import java.util.List;

public class Simulating {
    private final SensorEventGenerator sensorEventGenerator;
    List<EventHandler> eventHandlers;


    public Simulating(SensorEventGenerator sensorEventGenerator, List<EventHandler> eventHandlers) {
        this.sensorEventGenerator = sensorEventGenerator;
        this.eventHandlers = eventHandlers;
    }

    public void simulating(SmartHome smartHome){
        SensorEvent event = sensorEventGenerator.getNextSensorEvent();
        while (event != null){
            SensorEvent finalEvent = event;
            for(EventHandler it:eventHandlers){
                EventHandler eventHandler = new DecorateEventHandler(it);
                if(eventHandler.handle(finalEvent,smartHome)){
                    break;
                }
            };
            event = sensorEventGenerator.getNextSensorEvent();
        }
    }
}
