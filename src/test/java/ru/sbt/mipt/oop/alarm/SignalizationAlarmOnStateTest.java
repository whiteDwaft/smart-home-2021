package ru.sbt.mipt.oop.alarm;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.handlers.*;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.input.FileReader;
import ru.sbt.mipt.oop.input.HomeLoader;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SignalizationAlarmOnStateTest {

    @Test
    void setActivated_testFalse_whenSignalisationIsAlreadyActivated() {
        HomeLoader homeLoader = new FileReader("smart-home-1.js");
        SmartHome smartHome = homeLoader.loadFromFile();
        smartHome.formSignalizationObj();
        Signalization signalization = smartHome.getSignalization();
        signalization.changeState(new SignalizationAlarmOnState(signalization));
        List<EventHandler> list = Arrays.asList(new DoorEventHandler(),
                new LightEventHandler(),
                new HallDoorEventHandler(),
                new SignalizationEventHandler());
        EventHandler signalizationEventHandler = new AlarmDecorator(list);
        SensorEvent event = new SensorEvent(SensorEventType.SIGNALIZATION_ACTIVATED,123);
        signalizationEventHandler.handle(event,smartHome);
        SignalizationState signalizationState = signalization.getSignalizationState();
        boolean res = signalizationState instanceof SignalizationActivatedState;
        assertFalse(res,"signalization must be already activated");
    }

    @Test
    void setUnactivated_testTrue_whenSignalisationDisactivated() {
        HomeLoader homeLoader = new FileReader("smart-home-1.js");
        SmartHome smartHome = homeLoader.loadFromFile();
        smartHome.formSignalizationObj();
        Signalization signalization = smartHome.getSignalization();
        signalization.changeState(new SignalizationAlarmOnState(signalization));
        List<EventHandler> list = Arrays.asList(new DoorEventHandler(),
                new LightEventHandler(),
                new HallDoorEventHandler(),
                new SignalizationEventHandler());
        EventHandler signalizationEventHandler = new AlarmDecorator(list);
        SensorEvent event = new SensorEvent(SensorEventType.SIGNALIZATION_DISACTIVATED,123);
        signalizationEventHandler.handle(event,smartHome);
        SignalizationState signalizationState = signalization.getSignalizationState();
        boolean res = signalizationState instanceof SignalizationDisactivatedState;
        assertTrue(res,"signalization must be disactivated");
    }

    @Test
    void setUnactivated_testTrue_whenWrongPIN() {
        HomeLoader homeLoader = new FileReader("smart-home-1.js");
        SmartHome smartHome = homeLoader.loadFromFile();
        smartHome.formSignalizationObj();
        Signalization signalization = smartHome.getSignalization();
        signalization.changeState(new SignalizationAlarmOnState(signalization));
        List<EventHandler> list = Arrays.asList(new DoorEventHandler(),
                new LightEventHandler(),
                new HallDoorEventHandler(),
                new SignalizationEventHandler());
        EventHandler signalizationEventHandler = new AlarmDecorator(list);
        SensorEvent event = new SensorEvent(SensorEventType.SIGNALIZATION_DISACTIVATED,321);
        signalizationEventHandler.handle(event,smartHome);
        SignalizationState signalizationState = signalization.getSignalizationState();
        boolean res = signalizationState instanceof SignalizationDisactivatedState;
        assertFalse(res,"Wrong PIN was entered");
    }
}