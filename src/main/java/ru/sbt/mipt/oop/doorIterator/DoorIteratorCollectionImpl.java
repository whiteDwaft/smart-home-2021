package ru.sbt.mipt.oop.doorIterator;

import ru.sbt.mipt.oop.elements.Door;

import java.util.List;

public class DoorIteratorCollectionImpl implements DoorIteratorCollection {
    private final List<Door> doors;

    public DoorIteratorCollectionImpl(List<Door> doors) {
        this.doors = doors;
    }

    @Override
    public DoorIterator createIterator(int position) {
        return new DoorIteratorImpl(doors,position);
    }
}
