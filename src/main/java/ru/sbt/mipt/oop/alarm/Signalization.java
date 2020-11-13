package ru.sbt.mipt.oop.alarm;

public class Signalization {
    private SignalizationState signalizationState;
    final int pin;

    public Signalization(int PIN) {
        signalizationState = new SignalizationDisactivatedState(this);
        this.pin = PIN;
    }

    public SignalizationState getSignalizationState() {
        return signalizationState;
    }

    public void changeState(SignalizationState signalizationState){
        this.signalizationState = signalizationState;
    }
}
