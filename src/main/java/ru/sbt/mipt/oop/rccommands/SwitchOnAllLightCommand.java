package ru.sbt.mipt.oop.rccommands;

import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.SmartHome;

import java.util.List;

public class SwitchOnAllLightCommand implements RCCommand {
    private final SmartHome smartHome;

    public SwitchOnAllLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(lightObj -> {
            if(!(lightObj instanceof Light)){
                return;
            }
            Light light = (Light) lightObj;
            light.setOn(true);
        });
    }
}
