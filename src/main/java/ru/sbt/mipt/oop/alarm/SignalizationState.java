package ru.sbt.mipt.oop.alarm;

public abstract class SignalizationState {
    final Signalization signalization;

    SignalizationState(Signalization signalization) {
        this.signalization = signalization;
    }

    public abstract void setActivated(int PIN);

    public abstract void setUnactivated(int PIN);

    public abstract void switchAlarmOn();
}
