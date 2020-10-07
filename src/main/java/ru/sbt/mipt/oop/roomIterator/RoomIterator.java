package ru.sbt.mipt.oop.roomIterator;

import ru.sbt.mipt.oop.Room;

public interface RoomIterator {
    Room getNext();
    boolean hasMore();
}
