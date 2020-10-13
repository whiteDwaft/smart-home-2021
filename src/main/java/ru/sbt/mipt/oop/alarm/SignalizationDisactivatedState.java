package ru.sbt.mipt.oop.alarm;

public class SignalizationDisactivatedState extends SignalizationState {
    private String s = "disactivated";
    SignalizationDisactivatedState(Signalization alarm) {
        super(alarm);
    }

    @Override
    public boolean setActivated(int PIN) {
        if (PIN == signalization.PIN) {
            signalization.changeState(new SignalizationActivatedState(signalization));
            signalization.setActivated(true);
            System.out.println("ACTIVATED");
            return true;
        }
        System.out.println("INVALID PIN");
        return false;
    }

    @Override
    public boolean setUnactivated(int PIN) {
        System.out.println("ALREADY UNACTIVATED");
        return false;
    }

    @Override
    public boolean switchAlarmOn() {
        System.out.println("LOCKED");
        return false;
    }
}
