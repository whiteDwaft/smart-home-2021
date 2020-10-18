package ru.sbt.mipt.oop.alarm;

public class Signalization {
    private SignalizationState signalizationState;
//    private boolean isActivated;
    final int PIN;


    public Signalization(int PIN) {
        signalizationState = new SignalizationDisactivatedState(this);
        this.PIN = PIN;
    }

    public SignalizationState getSignalizationState() {
        return signalizationState;
    }

    public void changeState(SignalizationState signalizationState){
        this.signalizationState = signalizationState;
    }
}
