package ru.sbt.mipt.oop.alarm;

public class Signalization {
    private SignalizationState signalizationState;
    private boolean isActivated;
    final int PIN;


    public Signalization(int PIN) {
        signalizationState = new SignalizationDisactivatedState(this);
        this.PIN = PIN;
    }

    public boolean isActivated() {
        return isActivated;
    }

    void setActivated(boolean activated) {
        isActivated = activated;
    }

    public SignalizationState getSignalizationState() {
        return signalizationState;
    }

    public void changeState(SignalizationState signalizationState){
        this.signalizationState = signalizationState;
    }
}
