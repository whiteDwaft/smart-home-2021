package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;

public class SignalizationEventHandler implements EventHandler {
    @Override
    public boolean handle(SensorEvent sensorEvent, SmartHome smartHome) {
        if (sensorEvent.getType() == SensorEventType.SIGNALIZATION_ACTIVATED) {
            return smartHome.getSignalization().getSignalizationState().setActivated(sensorEvent.getPIN());
        } else if(sensorEvent.getType() == SensorEventType.SIGNALIZATION_DISACTIVATED){
            return smartHome.getSignalization().getSignalizationState().setUnactivated(sensorEvent.getPIN());
        }
        return false;
    }
}
