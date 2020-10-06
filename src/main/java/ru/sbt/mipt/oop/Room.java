package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.doorIterator.DoorIterator;
import ru.sbt.mipt.oop.doorIterator.DoorIteratorCollection;
import ru.sbt.mipt.oop.doorIterator.DoorIteratorCollectionImpl;
import ru.sbt.mipt.oop.lightIterator.LightIterator;
import ru.sbt.mipt.oop.lightIterator.LightIteratorCollection;
import ru.sbt.mipt.oop.lightIterator.LightIteratorCollectionImpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Room implements Action {
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
    public void execute(SensorEvent event) {
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            LightIteratorCollection lightIteratorCollection = new LightIteratorCollectionImpl(lights);
            LightIterator lightIterator = lightIteratorCollection.createIterator(-1);
            while (lightIterator.hasMore()) {
                lightIterator.getNext().execute(event);
            }
        } else {
            DoorIteratorCollection doorIteratorCollection = new DoorIteratorCollectionImpl(doors);
            DoorIterator doorIterator = doorIteratorCollection.createIterator(-1);
            while (doorIterator.hasMore()) {
                doorIterator.getNext().execute(event);
            }
            if(getName().equals("hall") && doors.stream().noneMatch(Door::isOpen)){
                LightIteratorCollection lightIteratorCollection = new LightIteratorCollectionImpl(lights);
                LightIterator lightIterator = lightIteratorCollection.createIterator(-1);
                while (lightIterator.hasMore()) {
                    lightIterator.getNext().setOn(false);
                }
            }
        }
    }
}
