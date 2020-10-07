package ru.sbt.mipt.oop.doorIterator;

import ru.sbt.mipt.oop.Door;

import java.util.List;

public class DoorIteratorImpl implements DoorIterator{
    private final List<Door> doors;
    private int position;

    public DoorIteratorImpl(List<Door> doors, int position) {
        this.doors = doors;
        this.position = position;
    }

    @Override
    public Door getNext() {
        if (hasMore()) {
            position++;
            return doors.get(position);
        } else return null;
    }

    @Override
    public boolean hasMore() {
        return position < doors.size() - 1;
    }
}
