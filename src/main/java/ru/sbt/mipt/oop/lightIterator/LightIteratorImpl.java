package ru.sbt.mipt.oop.lightIterator;

import ru.sbt.mipt.oop.Light;

import java.util.List;

public class LightIteratorImpl implements LightIterator {
    private final List<Light> lights;
    private int position;

    public LightIteratorImpl(List<Light> lights, int position) {
        this.lights = lights;
        this.position = position;
    }

    @Override
    public Light getNext() {
        if (hasMore()) {
            position++;
            return lights.get(position);
        } else return null;
    }

    @Override
    public boolean hasMore() {
        return position < lights.size() - 1;
    }
}
