package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;

public class SignalizationEventHandler implements EventHandler {
    @Override
    public void handle(SensorEvent sensorEvent, SmartHome smartHome) {
        if (sensorEvent.getType() == SensorEventType.SIGNALIZATION_ACTIVATED) {
            smartHome.getSignalization().setActivated(sensorEvent.getPIN());
        } else if(sensorEvent.getType() == SensorEventType.SIGNALIZATION_DISACTIVATED){
            smartHome.getSignalization().setUnActivated(sensorEvent.getPIN());
        }
    }
}
