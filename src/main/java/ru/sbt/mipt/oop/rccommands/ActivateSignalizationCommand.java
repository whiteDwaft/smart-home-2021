package ru.sbt.mipt.oop.rccommands;


import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.sensor.SensorEventType;

public class ActivateSignalizationCommand implements RCCommand {
    private final SmartHome smartHome;

    public ActivateSignalizationCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.getSignalization().getSignalizationState().setActivated(123);
    }
}
