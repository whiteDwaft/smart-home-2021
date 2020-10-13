package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.Signalization;
import ru.sbt.mipt.oop.roomIterator.RoomIterator;
import ru.sbt.mipt.oop.roomIterator.RoomIteratorCollection;
import ru.sbt.mipt.oop.roomIterator.RoomIteratorCollectionImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

//хотел добавть в SmartHome объект Signalisation, но при формировании объекта через JSON происходит StackOverflow
//(Signalization ссылвется на SignalizationState, а SignalizationState ссылается на Signalization (циклическая ссылка))
public class SmartHome  implements Action {
    List<Room> rooms;
    private final int PIN;
    private Signalization signalization;

    public SmartHome(int pin) {
        PIN = pin;
        rooms = new ArrayList<>();
    }

    public SmartHome(List<Room> rooms,int PIN)
    {
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

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }


    @Override
    public void executeOne(SensorEvent event) {
        RoomIteratorCollection roomIteratorCollection = new RoomIteratorCollectionImpl(rooms);
        RoomIterator roomIterator = roomIteratorCollection.createIterator(-1);
        while (roomIterator.hasMore())
        {
            roomIterator.getNext().executeOne(event);

        }
    }

    @Override
    public void executeAll(SensorEvent event) {
        RoomIteratorCollection roomIteratorCollection = new RoomIteratorCollectionImpl(rooms);
        RoomIterator roomIterator = roomIteratorCollection.createIterator(-1);
        while (roomIterator.hasMore())
        {
            roomIterator.getNext().executeAll(event);

        }
    }
}
