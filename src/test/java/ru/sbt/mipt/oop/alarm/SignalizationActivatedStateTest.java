package ru.sbt.mipt.oop.alarm;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.handlers.DecorateEventHandler;
import ru.sbt.mipt.oop.handlers.DoorEventHandler;
import ru.sbt.mipt.oop.handlers.EventHandler;
import ru.sbt.mipt.oop.handlers.SignalizationEventHandler;
import ru.sbt.mipt.oop.input.FileReader;
import ru.sbt.mipt.oop.input.HomeLoader;

import static org.junit.jupiter.api.Assertions.*;

class SignalizationActivatedStateTest {

    @Test
    void setActivated_testFalse_whenSignalisationIsAlreadyActivated() {
        HomeLoader homeLoader = new FileReader("smart-home-1.js");
        SmartHome smartHome = homeLoader.loadFromFile();
        smartHome.formSignalizationObj();
        Signalization signalization = smartHome.getSignalization();
        signalization.changeState(new SignalizationActivatedState(signalization));
        EventHandler signalizationEventHandler = new DecorateEventHandler(new SignalizationEventHandler());
        SensorEvent event = new SensorEvent(SensorEventType.SIGNALIZATION_ACTIVATED,123);
        boolean res = signalizationEventHandler.handle(event,smartHome);
        assertFalse(res,"signalization must be already activated");
    }

    @Test
    void setUnactivated_testTrue_whenSignalisationDisactivated() {
        HomeLoader homeLoader = new FileReader("smart-home-1.js");
        SmartHome smartHome = homeLoader.loadFromFile();
        smartHome.formSignalizationObj();
        Signalization signalization = smartHome.getSignalization();
        signalization.changeState(new SignalizationActivatedState(signalization));
        EventHandler signalizationEventHandler = new DecorateEventHandler(new SignalizationEventHandler());
        SensorEvent event = new SensorEvent(SensorEventType.SIGNALIZATION_DISACTIVATED,123);
        boolean res = signalizationEventHandler.handle(event,smartHome);
        assertTrue(res,"signalization must be disactivated");
    }

    @Test
    void setUnactivated_testTrue_whenWrongPIN() {
        HomeLoader homeLoader = new FileReader("smart-home-1.js");
        SmartHome smartHome = homeLoader.loadFromFile();
        smartHome.formSignalizationObj();
        Signalization signalization = smartHome.getSignalization();
        signalization.changeState(new SignalizationActivatedState(signalization));
        EventHandler signalizationEventHandler = new DecorateEventHandler(new SignalizationEventHandler());
        SensorEvent event = new SensorEvent(SensorEventType.SIGNALIZATION_DISACTIVATED,321);
        boolean res = signalizationEventHandler.handle(event,smartHome);
        assertFalse(res,"ALARM must be switched on");
    }
}