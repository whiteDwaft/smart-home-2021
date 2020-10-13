package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;

public class DecorateEventHandler implements EventHandler {
    private final EventHandler eventHandler;

    public DecorateEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public boolean handle(SensorEvent sensorEvent, SmartHome smartHome) {
        if (smartHome.getSignalization().isActivated() && (sensorEvent.getType() != SensorEventType.SIGNALIZATION_ACTIVATED &&
        sensorEvent.getType() != SensorEventType.SIGNALIZATION_DISACTIVATED)) {
            smartHome.getSignalization().getSignalizationState().switchAlarmOn();
            return true;
        } else {
            return eventHandler.handle(sensorEvent,smartHome);
        }
    }
}
