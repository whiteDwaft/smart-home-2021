package ru.sbt.mipt.oop.alarm;

public class SignalizationActivatedState extends SignalizationState {
    private String s = "activated";

    SignalizationActivatedState(Signalization signalization) {
        super(signalization);
    }

    @Override
    public void setActivated(int PIN) {
        System.out.println("ALREADY ACTIVATED");
    }

    @Override
    public void setUnactivated(int PIN) {
        if (PIN == signalization.PIN) {
            signalization.changeState(new SignalizationDisactivatedState(signalization));
            System.out.println("UNACTIVATED");
        } else {
            switchAlarmOn();
        }
    }

    @Override
    public void switchAlarmOn() {
        signalization.changeState(new SignalizationAlarmOnState(signalization));
        System.out.println("ALARM_ON");
    }
}
