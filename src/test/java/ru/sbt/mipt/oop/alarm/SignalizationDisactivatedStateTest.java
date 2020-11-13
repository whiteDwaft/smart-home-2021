package ru.sbt.mipt.oop.alarm;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.handlers.AlarmDecorator;
import ru.sbt.mipt.oop.handlers.EventHandler;
import ru.sbt.mipt.oop.handlers.SignalizationEventHandler;
import ru.sbt.mipt.oop.input.FileReader;
import ru.sbt.mipt.oop.input.HomeLoader;

import static org.junit.jupiter.api.Assertions.*;

class SignalizationDisactivatedStateTest {

//    @Test
//    void setActivated_testTrue_whenSignalisationActivated() {
//        HomeLoader homeLoader = new FileReader("smart-home-1.js");
//        SmartHome smartHome = homeLoader.loadFromFile();
//        smartHome.formSignalizationObj();
//        EventHandler signalizationEventHandler = new AlarmDecorator(new SignalizationEventHandler());
//        SensorEvent event = new SensorEvent(SensorEventType.SIGNALIZATION_ACTIVATED,123);
//        signalizationEventHandler.handle(event,smartHome);
//        SignalizationState signalizationState = smartHome.getSignalization().getSignalizationState();
//        boolean res = signalizationState instanceof SignalizationActivatedState;
//        assertTrue(res,"signalization must be  activated");
//    }
//
//    @Test
//    void setUnactivated_testFalse_whenSignalisationIsAlreadyDisactivated() {
//        HomeLoader homeLoader = new FileReader("smart-home-1.js");
//        SmartHome smartHome = homeLoader.loadFromFile();
//        smartHome.formSignalizationObj();
//        EventHandler signalizationEventHandler = new AlarmDecorator(new SignalizationEventHandler());
//        SensorEvent event = new SensorEvent(SensorEventType.SIGNALIZATION_DISACTIVATED,123);
//        signalizationEventHandler.handle(event,smartHome);
//        SignalizationState signalizationState = smartHome.getSignalization().getSignalizationState();
//        boolean res = signalizationState instanceof SignalizationActivatedState;
//        assertFalse(res,"signalization must be already disactivated");
//    }

}