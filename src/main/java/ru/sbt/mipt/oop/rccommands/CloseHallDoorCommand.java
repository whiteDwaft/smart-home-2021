package ru.sbt.mipt.oop.rccommands;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.elements.Door;
import ru.sbt.mipt.oop.elements.Room;

public class CloseHallDoorCommand implements RCCommand {
    private final SmartHome smartHome;

    public CloseHallDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(roomObj ->{
            if(!(roomObj instanceof Room)){
                return;
            }
            Room room = (Room)roomObj;
            if(!(room.getName().equals("hall"))){
                return;
            }
            room.execute(doorObj ->{
                if(!(doorObj instanceof Door)){
                    return;
                }
                Door door = (Door) doorObj;
                door.setOpen(false);
            });
        });
    }
}
