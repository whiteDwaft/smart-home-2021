package ru.sbt.mipt.oop.rc;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.input.FileReader;
import ru.sbt.mipt.oop.input.HomeLoader;
import ru.sbt.mipt.oop.rccommands.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SmartHomeRemoteControlTest {

    @Test
    void onButtonPressed_whenRcIdOfRemoteControlNotEqualRcIdOfOnButtonPressed() {
        HomeLoader homeLoader = new FileReader("smart-home-1.js");
        SmartHome smartHome = homeLoader.loadFromFile();
        smartHome.formSignalizationObj();
        RemoteControl remoteControl = new SmartHomeRemoteControl(getrcDescriptor(
                new ActivateSignalizationCommand(smartHome),
                new AlarmOnCommand(smartHome),
                new CloseHallDoorCommand(smartHome),
                new SwitchOffAllLightCommand(smartHome),
                new SwitchOffHallLightCommand(smartHome),
                new SwitchOnAllLightCommand(smartHome)
        ),"1");
        assertThrows(IllegalArgumentException.class,() ->{remoteControl.onButtonPressed("A","2");});


    }
    Map<String, RCCommand> getrcDescriptor(
            RCCommand ActivateSignalizationCommand,
            RCCommand AlarmOnCommand,
            RCCommand CloseHallDoorCommand,
            RCCommand SwitchOffAllLightCommand,
            RCCommand SwitchOffHallLightCommand,
            RCCommand SwitchOnAllLightCommand
    ) {
        Map<String, RCCommand> rcDescriptor = new HashMap<>();
        rcDescriptor.put("A", ActivateSignalizationCommand);
        rcDescriptor.put("B", AlarmOnCommand);
        rcDescriptor.put("C", CloseHallDoorCommand);
        rcDescriptor.put("D", SwitchOffAllLightCommand);
        rcDescriptor.put("1", SwitchOffHallLightCommand);
        rcDescriptor.put("2", SwitchOnAllLightCommand);
        return rcDescriptor;
    }
}