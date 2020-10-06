package ru.sbt.mipt.oop.doorIterator;

import ru.sbt.mipt.oop.Door;

public interface DoorIterator {
    Door getNext();
    boolean hasMore();
}
