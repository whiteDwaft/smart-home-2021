package ru.sbt.mipt.oop.rccommands;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.elements.Room;
import ru.sbt.mipt.oop.input.FileReader;
import ru.sbt.mipt.oop.input.HomeLoader;

import static org.junit.jupiter.api.Assertions.*;

class SwitchOffHallLightCommandTest {

    @Test
    void execute() {
        HomeLoader homeLoader = new FileReader("smart-home-1.js");
        SmartHome smartHome = homeLoader.loadFromFile();
        smartHome.formSignalizationObj();
        RCCommand rcCommand = new SwitchOffHallLightCommand(smartHome);
        rcCommand.execute();
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
                assertFalse(door.isOn());
            });
        });
    }
}