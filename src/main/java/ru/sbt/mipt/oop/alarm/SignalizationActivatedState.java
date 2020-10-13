package ru.sbt.mipt.oop.alarm;

public class SignalizationActivatedState extends SignalizationState {
    private String s = "activated";
    SignalizationActivatedState(Signalization signalization) {
        super(signalization);
    }

    @Override
    public boolean setActivated(int PIN) {
        System.out.println("ALREADY ACTIVATED");
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
        return switchAlarmOn();
    }

    @Override
    public boolean switchAlarmOn() {
        signalization.changeState(new SignalizationAlarmOnState(signalization));
        System.out.println("ALARM_ON");
        return false;
    }
}
