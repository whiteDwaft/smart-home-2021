package ru.sbt.mipt.oop;

public class SensorEvent {
    private final SensorEventType type;
    private String objectId;
    private int PIN;

    public SensorEvent(SensorEventType type, String objectId) {
        this.type = type;
        this.objectId = objectId;
    }

    public SensorEvent(SensorEventType type, int PIN) {
        this.type = type;
        this.PIN = PIN;
    }

    public int getPIN() {
        return PIN;
    }

    public SensorEventType getType() {
        return type;
    }

    public String getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }

}
