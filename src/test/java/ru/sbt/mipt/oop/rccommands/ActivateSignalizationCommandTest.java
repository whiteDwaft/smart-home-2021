package ru.sbt.mipt.oop.rccommands;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.SignalizationActivatedState;
import ru.sbt.mipt.oop.alarm.SignalizationState;
import ru.sbt.mipt.oop.input.FileReader;
import ru.sbt.mipt.oop.input.HomeLoader;

import static org.junit.jupiter.api.Assertions.*;

class ActivateSignalizationCommandTest {


    @Test
    void execute_whenSignalisationInDeactivationState() {
        HomeLoader homeLoader = new FileReader("smart-home-1.js");
        SmartHome smartHome = homeLoader.loadFromFile();
        smartHome.formSignalizationObj();
        RCCommand rcCommand = new ActivateSignalizationCommand(smartHome);
        rcCommand.execute();
        SignalizationState signalizationState = smartHome.getSignalization().getSignalizationState();
        assertEquals(SignalizationActivatedState.class, signalizationState.getClass());
    }
    @Test
    void execute_whenSignalisationInActivationState() {
        HomeLoader homeLoader = new FileReader("smart-home-1.js");
        SmartHome smartHome = homeLoader.loadFromFile();
        smartHome.formSignalizationObj();
        smartHome.getSignalization().getSignalizationState().setActivated(123);
        RCCommand rcCommand = new ActivateSignalizationCommand(smartHome);
        rcCommand.execute();
        SignalizationState signalizationState = smartHome.getSignalization().getSignalizationState();
        assertEquals(SignalizationActivatedState.class, signalizationState.getClass());
    }

}