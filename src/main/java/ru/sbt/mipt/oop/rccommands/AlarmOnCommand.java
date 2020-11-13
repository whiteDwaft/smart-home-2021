package ru.sbt.mipt.oop.rccommands;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.SignalizationAlarmOnState;

public class AlarmOnCommand implements RCCommand {
    private final SmartHome smartHome;

    public AlarmOnCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.getSignalization().getSignalizationState().switchAlarmOn();
    }
}
