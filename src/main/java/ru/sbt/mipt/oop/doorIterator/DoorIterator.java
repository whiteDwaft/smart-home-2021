package ru.sbt.mipt.oop.doorIterator;

import ru.sbt.mipt.oop.elements.Door;

public interface DoorIterator {
    Door getNext();
    boolean hasMore();
}
