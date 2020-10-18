package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.Signalization;
import ru.sbt.mipt.oop.elements.Room;
import ru.sbt.mipt.oop.roomIterator.RoomIterator;
import ru.sbt.mipt.oop.roomIterator.RoomIteratorCollection;
import ru.sbt.mipt.oop.roomIterator.RoomIteratorCollectionImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

//хотел добавть в SmartHome объект Signalisation, но при формировании объекта через JSON происходит StackOverflow
//(Signalization ссылвется на SignalizationState, а SignalizationState ссылается на Signalization (циклическая ссылка))
public class SmartHome  implements Actionable {
    List<Room> rooms;
    private final int PIN;
    private final RoomIteratorCollection roomIteratorCollection;
    private Signalization signalization;

    public SmartHome(int pin) {
        PIN = pin;
        rooms = new ArrayList<>();
        roomIteratorCollection = new RoomIteratorCollectionImpl(rooms);
    }

    public SmartHome(List<Room> rooms,int PIN)
    {
        roomIteratorCollection = new RoomIteratorCollectionImpl(rooms);
        this.rooms = rooms;
        this.PIN = PIN;
    }

    public Signalization getSignalization() {
        return signalization;
    }
        public void formSignalizationObj(){
        signalization = new Signalization(PIN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartHome smartHome = (SmartHome) o;
        return Objects.equals(rooms, smartHome.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rooms);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }


    @Override
    public void execute(Action action) {
        action.accept(this);

        RoomIterator roomIterator = roomIteratorCollection.createIterator(-1);
        while (roomIterator.hasMore())
        {
            roomIterator.getNext().execute(action);
        }
    }
}
