package ru.sbt.mipt.oop.rccommands;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.SignalizationAlarmOnState;
import ru.sbt.mipt.oop.alarm.SignalizationDisactivatedState;
import ru.sbt.mipt.oop.alarm.SignalizationState;
import ru.sbt.mipt.oop.input.FileReader;
import ru.sbt.mipt.oop.input.HomeLoader;

import static org.junit.jupiter.api.Assertions.*;

class AlarmOnCommandTest {

    @Test
    void execute_whenSignalisationInActivationState() {
        HomeLoader homeLoader = new FileReader("smart-home-1.js");
        SmartHome smartHome = homeLoader.loadFromFile();
        smartHome.formSignalizationObj();
        smartHome.getSignalization().getSignalizationState().setActivated(123);
        RCCommand rcCommand = new AlarmOnCommand(smartHome);
        rcCommand.execute();
        SignalizationState signalizationState = smartHome.getSignalization().getSignalizationState();
        assertEquals(SignalizationAlarmOnState.class,signalizationState.getClass());
    }
    @Test
    void execute_whenSignalisationInDeactivationState() {
        HomeLoader homeLoader = new FileReader("smart-home-1.js");
        SmartHome smartHome = homeLoader.loadFromFile();
        smartHome.formSignalizationObj();
        RCCommand rcCommand = new AlarmOnCommand(smartHome);
        rcCommand.execute();
        SignalizationState signalizationState = smartHome.getSignalization().getSignalizationState();
        assertEquals(SignalizationDisactivatedState.class,signalizationState.getClass());
    }
}