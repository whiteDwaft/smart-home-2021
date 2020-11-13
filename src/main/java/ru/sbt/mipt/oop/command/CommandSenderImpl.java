package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.command.CommandSender;

public class CommandSenderImpl implements CommandSender {

    @Override
    public void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }

}
