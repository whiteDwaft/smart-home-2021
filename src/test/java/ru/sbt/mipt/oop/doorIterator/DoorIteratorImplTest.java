package ru.sbt.mipt.oop.doorIterator;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.elements.Door;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DoorIteratorImplTest {

    @Test
    void getNext() {
        List<Door> doors = new ArrayList<>();
        doors.add(new Door(false,""+1));
        doors.add(new Door(false,""+2));
        DoorIteratorCollection doorIteratorCollection = new DoorIteratorCollectionImpl(doors);
        DoorIterator doorIterator = doorIteratorCollection.createIterator(-1);
        Door res = doorIterator.getNext();
        assertEquals(doors.get(0),res,"doors must be equals");

    }

    @Test
    void hasMore() {
        List<Door> doors = new ArrayList<>();
        doors.add(new Door(false,""+1));
        doors.add(new Door(false,""+2));
        DoorIteratorCollection doorIteratorCollection = new DoorIteratorCollectionImpl(doors);
        DoorIterator doorIterator = doorIteratorCollection.createIterator(-1);
        boolean res = doorIterator.hasMore();
        assertTrue(res,"iterator is not on the last element");
    }
}