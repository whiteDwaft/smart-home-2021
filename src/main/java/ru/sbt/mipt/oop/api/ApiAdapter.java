package ru.sbt.mipt.oop.api;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;

import java.util.Map;

public class ApiAdapter implements EventHandler {
    private final ru.sbt.mipt.oop.handlers.EventHandler eventHandler;
    private final SmartHome smartHome;
    private final Map<String, SensorEventType> conventor;


    public ApiAdapter(ru.sbt.mipt.oop.handlers.EventHandler eventHandler, SmartHome smartHome, Map<String, SensorEventType> conventor) {
        this.eventHandler = eventHandler;
        this.smartHome = smartHome;
        this.conventor = conventor;
    }

    @Override
    public void handleEvent(CCSensorEvent ccSensorEvent) {

        SensorEvent sensorEvent = new SensorEvent(conventor.get(ccSensorEvent.getEventType()),ccSensorEvent.getObjectId());
        eventHandler.handle(sensorEvent,smartHome);

    }
}
