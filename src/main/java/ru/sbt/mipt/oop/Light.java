package ru.sbt.mipt.oop;

import java.util.Objects;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class Light implements Action {
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Light light = (Light) o;
        return isOn == light.isOn &&
                Objects.equals(id, light.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isOn, id);
    }

    public boolean isOn() {
        return isOn;
    }

    public String getId() {
        return id;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public void execute(SensorEvent event) {
        if (getId().equals(event.getObjectId())) {
            if (event.getType() == LIGHT_ON) {
                setOn(true);
                System.out.println("Light " + getId() + " was turned on.");
            } else {
                setOn(false);
                System.out.println("Light " + getId() + " was turned off.");
            }
        }
    }
}
