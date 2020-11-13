package ru.sbt.mipt.oop.rccommands;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.elements.Room;

public class SwitchOffHallLightCommand implements RCCommand {
    private final SmartHome smartHome;

    public SwitchOffHallLightCommand(SmartHome smartHome) {
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
            room.execute(lightObj ->{
                if(!(lightObj instanceof Light)){
                    return;
                }
                Light door = (Light) lightObj;
                door.setOn(false);
            });
        });
    }
}
