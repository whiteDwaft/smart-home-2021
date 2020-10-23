package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Room implements Actionable {
    private List<Light> lights;
    private List<Door> doors;
    private String name;

    public Room(List<Light> lights, List<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(lights, room.lights) &&
                Objects.equals(doors, room.doors) &&
                Objects.equals(name, room.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lights, doors, name);
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        action.accept(this);

        lights.forEach(light -> light.execute(action));
        doors.forEach(door -> door.execute(action));
    }
}
