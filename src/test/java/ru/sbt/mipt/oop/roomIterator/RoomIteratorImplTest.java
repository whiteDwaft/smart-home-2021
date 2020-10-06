package ru.sbt.mipt.oop.roomIterator;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomIteratorImplTest {

    @Test
    void getNext() {
        List<Light> lights = new ArrayList<>();
        lights.add(new Light(""+1,false));
        lights.add(new Light(""+2,false));

        List<Door> doors = new ArrayList<>();
        doors.add(new Door(false,""+1));
        doors.add(new Door(false,""+2));

        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(lights,doors,"room"));

        RoomIteratorCollection roomIteratorCollection = new RoomIteratorCollectionImpl(rooms);
        RoomIterator roomIterator = roomIteratorCollection.createIterator(-1);
        Room room = roomIterator.getNext();
        assertEquals(rooms.get(0),room,"rooms must be equals");
    }

    @Test
    void hasMore() {
        List<Light> lights = new ArrayList<>();
        lights.add(new Light(""+1,false));
        lights.add(new Light(""+2,false));

        List<Door> doors = new ArrayList<>();
        doors.add(new Door(false,""+1));
        doors.add(new Door(false,""+2));

        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(lights,doors,"room"));

        RoomIteratorCollection roomIteratorCollection = new RoomIteratorCollectionImpl(rooms);
        RoomIterator roomIterator = roomIteratorCollection.createIterator(-1);
        boolean res = roomIterator.hasMore();
        assertTrue(res,"iterator is not on the last element");
    }
}