package ru.sbt.mipt.oop.rc;

import ru.sbt.mipt.oop.rccommands.RCCommand;

import java.util.Map;

public class RemoteControl1 implements RemoteControl {
    private final Map<String, RCCommand> rcDescriptor;
    private final String rcid;

    public String getRcid() {
        return rcid;
    }

    public RemoteControl1(Map<String, RCCommand> rcDescriptor, String rcid) {
        this.rcDescriptor = rcDescriptor;
        this.rcid = rcid;
    }


    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (!rcId.equals(this.rcid)) {
            throw new IllegalArgumentException();
        }
        if(rcDescriptor.containsKey(buttonCode)){
            rcDescriptor.get(buttonCode).execute();
        }
    }
}
