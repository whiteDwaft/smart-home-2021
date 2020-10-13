package ru.sbt.mipt.oop.alarm;

public abstract class SignalizationState {
    final Signalization signalization;

    SignalizationState(Signalization signalization) {
        this.signalization = signalization;
    }

    public abstract boolean setActivated(int PIN);

    public abstract boolean setUnactivated(int PIN);

    public abstract boolean switchAlarmOn();
}
