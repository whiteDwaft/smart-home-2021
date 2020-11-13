package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;

import java.util.Objects;

public class Light implements Actionable {
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
    public void execute(Action action) {
        action.accept(this);
    }

}
