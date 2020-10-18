package ru.sbt.mipt.oop.lightIterator;

import ru.sbt.mipt.oop.elements.Light;

import java.util.List;

public class LightIteratorCollectionImpl implements LightIteratorCollection {
    private final List<Light> lights;

    public LightIteratorCollectionImpl(List<Light> lights) {
        this.lights = lights;
    }

    @Override
    public LightIterator createIterator(int position) {
        return new LightIteratorImpl(lights,position);
    }
}
