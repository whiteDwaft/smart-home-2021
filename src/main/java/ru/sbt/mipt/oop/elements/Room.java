package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.doorIterator.DoorIterator;
import ru.sbt.mipt.oop.doorIterator.DoorIteratorCollection;
import ru.sbt.mipt.oop.doorIterator.DoorIteratorCollectionImpl;
import ru.sbt.mipt.oop.lightIterator.LightIterator;
import ru.sbt.mipt.oop.lightIterator.LightIteratorCollection;
import ru.sbt.mipt.oop.lightIterator.LightIteratorCollectionImpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Room implements Actionable {
    private List<Light> lights;
    private List<Door> doors;
    private String name;
    private final LightIteratorCollection lightIteratorCollection;
    private final DoorIteratorCollection doorIteratorCollection;

    public Room(List<Light> lights, List<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
        lightIteratorCollection = new LightIteratorCollectionImpl(lights);
        doorIteratorCollection = new DoorIteratorCollectionImpl(doors);
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

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        action.accept(this);

        LightIterator lightIterator = lightIteratorCollection.createIterator(-1);
        while (lightIterator.hasMore()) {
            lightIterator.getNext().execute(action);
        }

        DoorIterator doorIterator = doorIteratorCollection.createIterator(-1);
        while (doorIterator.hasMore()) {
            doorIterator.getNext().execute(action);
        }
    }
}
