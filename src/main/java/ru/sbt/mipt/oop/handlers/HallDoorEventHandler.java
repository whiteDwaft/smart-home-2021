package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.*;

import java.util.Collections;

public class HallDoorEventHandler implements EventHandler {

    @Override
    public boolean handle(SensorEvent event, SmartHome smartHome) {
        if(event.getType() == SensorEventType.DOOR_CLOSED){
            for(Room room:smartHome.getRooms()){
                if(room.getName().equals("hall") &&
                        room.getDoors().stream().anyMatch(it -> it.getId().equals(event.getObjectId())))
                {
                    SensorEvent newEvent = new SensorEvent(SensorEventType.LIGHT_OFF,null);
                    smartHome.executeAll(newEvent);
                }
            }
        }
        return false;
//
    }
}
