package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.SignalizationActivatedState;

public class DecorateEventHandler implements EventHandler {
    private final EventHandler eventHandler;

    public DecorateEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void handle(SensorEvent sensorEvent, SmartHome smartHome) {
        if (smartHome.getSignalization().getSignalizationState().getClass() == SignalizationActivatedState.class && (sensorEvent.getType() != SensorEventType.SIGNALIZATION_ACTIVATED &&
        sensorEvent.getType() != SensorEventType.SIGNALIZATION_DISACTIVATED)) {
            smartHome.getSignalization().getSignalizationState().switchAlarmOn();
        } else {
            eventHandler.handle(sensorEvent,smartHome);
        }
    }
}
