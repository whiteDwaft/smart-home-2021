package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.SignalizationActivatedState;

import java.util.List;

public class AlarmDecorator implements EventHandler {
    private final List<EventHandler> eventHandlers;

    public AlarmDecorator(List<EventHandler> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    @Override
    public void handle(SensorEvent sensorEvent, SmartHome smartHome) {
        eventHandlers.forEach(eventHandler -> {
            if (smartHome.getSignalization().getSignalizationState().getClass() == SignalizationActivatedState.class && (sensorEvent.getType() != SensorEventType.SIGNALIZATION_ACTIVATED &&
                    sensorEvent.getType() != SensorEventType.SIGNALIZATION_DISACTIVATED)) {
                smartHome.getSignalization().getSignalizationState().switchAlarmOn();
                System.out.println("Sending sms");
            } else {
                eventHandler.handle(sensorEvent, smartHome);
            }
        });
    }
}
