package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;

import java.util.Objects;

public class Door implements Actionable {
    private final String id;

    public boolean isOpen() {
        return isOpen;
    }

    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Door door = (Door) o;
        return isOpen == door.isOpen &&
                Objects.equals(id, door.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isOpen);
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public void execute(Action action) {
        action.accept(this);
    }
}

