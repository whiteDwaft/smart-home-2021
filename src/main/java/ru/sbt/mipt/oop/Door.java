package ru.sbt.mipt.oop;

import java.util.Objects;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class Door implements Action {
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
    public void executeOne(SensorEvent event) {
        if (getId().equals(event.getObjectId())) {
            if (event.getType() == DOOR_OPEN) {
                setOpen(true);
                System.out.println("Door " + getId() + " was opened.");

            } else {
                setOpen(false);
                System.out.println("Door " + getId() + " was closed.");
            }
        }
    }

    @Override
    public void executeAll(SensorEvent event) {
        if (event.getType() == DOOR_OPEN) {
            setOpen(true);
            System.out.println("Door " + getId() + " was opened.");

        } else {
            setOpen(false);
            System.out.println("Door " + getId() + " was closed.");
        }
    }
}

