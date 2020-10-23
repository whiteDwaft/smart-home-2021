package ru.sbt.mipt.oop.alarm;

public class SignalizationDisactivatedState implements SignalizationState {
    private final Signalization signalization;
    private String s = "disactivated";

    SignalizationDisactivatedState(Signalization alarm) {
        this.signalization = alarm;
    }

    @Override
    public void setActivated(int PIN) {
        if (PIN == signalization.pin) {
            signalization.changeState(new SignalizationActivatedState(signalization));
            System.out.println("ACTIVATED");
        } else {
            System.out.println("INVALID PIN");
        }
    }

    @Override
    public void setUnactivated(int PIN) {
        System.out.println("ALREADY UNACTIVATED");
    }

    @Override
    public void switchAlarmOn() {
        System.out.println("LOCKED");
    }
}
