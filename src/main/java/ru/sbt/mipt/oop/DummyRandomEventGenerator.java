package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventGenerator;
import ru.sbt.mipt.oop.sensor.SensorEventType;

class DummyRandomEventGenerator implements SensorEventGenerator {

    @Override
    public SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (6 * Math.random())];
        if (sensorEventType == SensorEventType.SIGNALIZATION_ACTIVATED ||
                sensorEventType == SensorEventType.SIGNALIZATION_DISACTIVATED){
            int PIN = 123;
            return new SensorEvent(sensorEventType, PIN);
        }
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
