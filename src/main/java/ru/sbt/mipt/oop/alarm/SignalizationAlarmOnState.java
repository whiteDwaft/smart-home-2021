package ru.sbt.mipt.oop.alarm;

public class SignalizationAlarmOnState extends SignalizationState {
    private String s = "alarm";

    SignalizationAlarmOnState(Signalization alarm) {
        super(alarm);
    }

    @Override
    public void setActivated(int PIN) {
        System.out.println("LOCKED");
    }

    @Override
    public void setUnactivated(int PIN) {
        if (PIN == signalization.PIN) {
            signalization.changeState(new SignalizationDisactivatedState(signalization));
            System.out.println("UNACTIVATED");
        } else {
            System.out.println("INVALID PIN");
        }
    }

    @Override
    public void switchAlarmOn() {
        System.out.println("ALREADY SWITCHED ON");
    }
}
