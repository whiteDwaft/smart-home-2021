package ru.sbt.mipt.oop.lightIterator;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.elements.Light;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LightIteratorImplTest {

    @Test
    void getNext() {
        List<Light> lights = new ArrayList<>();
        lights.add(new Light(""+1,false));
        lights.add(new Light(""+2,false));
        LightIteratorCollection lightIteratorCollection = new LightIteratorCollectionImpl(lights);
        LightIterator lightIterator = lightIteratorCollection.createIterator(-1);
        Light light = lightIterator.getNext();
        assertEquals(lights.get(0),light,"it must be equals");

    }

    @Test
    void hasMore() {
        List<Light> lights = new ArrayList<>();
        lights.add(new Light(""+1,false));
        lights.add(new Light(""+2,false));
        LightIteratorCollection lightIteratorCollection = new LightIteratorCollectionImpl(lights);
        LightIterator lightIterator = lightIteratorCollection.createIterator(-1);
        boolean res = lightIterator.hasMore();
        assertTrue(res,"iterator is not on the last element");
    }
}