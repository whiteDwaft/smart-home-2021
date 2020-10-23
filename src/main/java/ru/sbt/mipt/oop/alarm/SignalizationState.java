package ru.sbt.mipt.oop.alarm;

public interface SignalizationState {

    void setActivated(int PIN);

    void setUnactivated(int PIN);

    void switchAlarmOn();
}
