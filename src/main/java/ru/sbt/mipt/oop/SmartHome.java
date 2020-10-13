package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.roomIterator.RoomIterator;
import ru.sbt.mipt.oop.roomIterator.RoomIteratorCollection;
import ru.sbt.mipt.oop.roomIterator.RoomIteratorCollectionImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class SmartHome  implements Action {
    List<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartHome smartHome = (SmartHome) o;
        return Objects.equals(rooms, smartHome.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rooms);
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }


    @Override
    public void execute(SensorEvent sensorEvent) {
        RoomIteratorCollection roomIteratorCollection = new RoomIteratorCollectionImpl(rooms);
        RoomIterator roomIterator = roomIteratorCollection.createIterator(-1);
        while (roomIterator.hasMore())
        {
            roomIterator.getNext().execute(sensorEvent);

        }
    }
}
