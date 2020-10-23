package ru.sbt.mipt.oop.alarm;

public class SignalizationAlarmOnState implements SignalizationState {
    private final Signalization signalization;
    private String s = "alarm";

    SignalizationAlarmOnState(Signalization alarm) {
        this.signalization = alarm;
    }

    @Override
    public void setActivated(int PIN) {
        System.out.println("LOCKED");
    }

    @Override
    public void setUnactivated(int PIN) {
        if (PIN == signalization.pin) {
            signalization.changeState(new SignalizationDisactivatedState(signalization));
            System.out.println("UNACTIVATED");
        } else {
            System.out.println("INVALID PIN");
        }
    }

    @Override
    public void switchAlarmOn() {
        System.out.println("ALREADY SWITCHED ON");
        System.out.println("Sending sms");
    }
}
