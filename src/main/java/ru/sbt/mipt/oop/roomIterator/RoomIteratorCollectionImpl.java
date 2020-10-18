package ru.sbt.mipt.oop.roomIterator;

import ru.sbt.mipt.oop.elements.Room;

import java.util.List;

public class RoomIteratorCollectionImpl implements RoomIteratorCollection {
    private final List<Room> rooms;

    public RoomIteratorCollectionImpl(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public RoomIterator createIterator(int position) {
        return new RoomIteratorImpl(rooms,position);
    }
}
