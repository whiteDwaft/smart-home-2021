package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.handlers.AlarmDecorator;
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
            for(EventHandler it:eventHandlers){
//                EventHandler eventHandler = new AlarmDecorator(it);
//                eventHandler.handle(finalEvent,smartHome);
            }
            event = sensorEventGenerator.getNextSensorEvent();
        }
    }
}
