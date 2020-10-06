package ru.sbt.mipt.oop.lightIterator;

import ru.sbt.mipt.oop.Light;

public interface LightIterator {
    Light getNext();
    boolean hasMore();
}
