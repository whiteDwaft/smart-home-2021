package ru.sbt.mipt.oop.roomIterator;

import ru.sbt.mipt.oop.elements.Room;

import java.util.List;

public class RoomIteratorImpl implements RoomIterator {
    private List<Room> rooms;
    private int position;

    public RoomIteratorImpl(List<Room> rooms,int position) {
        this.rooms = rooms;
        this.position = position;
    }


    @Override
    public Room getNext() {
        if(hasMore()){
            position++;
            return rooms.get(position);
        }
        else return null;
    }

    @Override
    public boolean hasMore() {
        return position < rooms.size()-1;
    }
}
