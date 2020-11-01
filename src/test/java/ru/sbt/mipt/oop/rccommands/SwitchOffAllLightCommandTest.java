package ru.sbt.mipt.oop.rccommands;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.input.FileReader;
import ru.sbt.mipt.oop.input.HomeLoader;

import static org.junit.jupiter.api.Assertions.*;

class SwitchOffAllLightCommandTest {

    @Test
    void execute() {
        HomeLoader homeLoader = new FileReader("smart-home-1.js");
        SmartHome smartHome = homeLoader.loadFromFile();
        smartHome.formSignalizationObj();
        RCCommand rcCommand = new SwitchOffAllLightCommand(smartHome);
        rcCommand.execute();
        smartHome.execute(lightObj ->{
            if(!(lightObj instanceof Light)){
                return;
            }
            Light light = (Light) lightObj;
            assertFalse(light.isOn());
        });
    }
}