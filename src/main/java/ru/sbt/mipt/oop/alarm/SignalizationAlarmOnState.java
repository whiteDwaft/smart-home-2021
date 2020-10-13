package ru.sbt.mipt.oop.alarm;

public class SignalizationAlarmOnState extends SignalizationState {
    private String s = "alarm";
    SignalizationAlarmOnState(Signalization alarm) {
        super(alarm);
    }

    @Override
    public boolean setActivated(int PIN) {
        System.out.println("LOCKED");
        return false;
    }

    @Override
    public boolean setUnactivated(int PIN) {
        if (PIN == signalization.PIN) {
            signalization.changeState(new SignalizationDisactivatedState(signalization));
            signalization.setActivated(false);
            System.out.println("UNACTIVATED");
            return true;
        }
        System.out.println("INVALID PIN");
        return false;
    }

    @Override
    public boolean switchAlarmOn() {
        System.out.println("ALREADY SWITCHED ON");
        return false;
    }
}
