package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.*;

public class HallDoorEventHandler implements EventHandler {
    private final CommandSender commandSender;
    private final SensorCommand sensorCommand;

    public HallDoorEventHandler(SensorCommand sensorCommand) {
        this.sensorCommand = sensorCommand;
        this.commandSender = new CommandSenderImpl();;
    }

    @Override
    public void handle(SensorEvent event, SmartHome smartHome) {
        smartHome.execute(event);
        SensorCommand sensorCommand = new SensorCommand(CommandType.Light_ON, event.getObjectId());
        commandSender.sendCommand(sensorCommand);
    }
}
