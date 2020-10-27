package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.handlers.AlarmDecorator;
import ru.sbt.mipt.oop.handlers.EventHandler;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventGenerator;

import java.util.List;

public class LoopEventGenerator {
    private final SensorEventGenerator sensorEventGenerator;
    List<EventHandler> eventHandlers;
    private final EventHandler decorator;


    public LoopEventGenerator(SensorEventGenerator sensorEventGenerator, List<EventHandler> eventHandlers) {
        this.sensorEventGenerator = sensorEventGenerator;
        this.eventHandlers = eventHandlers;
        decorator = new AlarmDecorator(this.eventHandlers);
    }

    public void simulating(SmartHome smartHome) {
        SensorEvent event = sensorEventGenerator.getNextSensorEvent();
        while (event != null) {
            decorator.handle(event, smartHome);
            event = sensorEventGenerator.getNextSensorEvent();
        }
    }
}
