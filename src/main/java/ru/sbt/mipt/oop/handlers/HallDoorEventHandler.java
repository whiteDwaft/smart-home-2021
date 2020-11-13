package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.command.CommandSenderImpl;
import ru.sbt.mipt.oop.command.CommandType;
import ru.sbt.mipt.oop.elements.Door;
import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.elements.Room;
import ru.sbt.mipt.oop.sensor.SensorEvent;

import static ru.sbt.mipt.oop.sensor.SensorEventType.*;

public class HallDoorEventHandler implements EventHandler {
    private final CommandSender commandSender;

    public HallDoorEventHandler() {
        this.commandSender = new CommandSenderImpl();
    }

    @Override
    public void handle(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == DOOR_CLOSED || event.getType() == DOOR_OPEN) {
            smartHome.execute(roomObj -> {
                if (!(roomObj instanceof Room)) {
                    return;
                }
                Room room = (Room) roomObj;
                if (!room.getName().equals("hall")) {
                    return;
                }
                room.execute(doorObj -> {
                    if (!(doorObj instanceof Door)) {
                        return;
                    }
                    Door door = (Door) doorObj;
                    if (!door.getId().equals(event.getObjectId())) {
                        return;
                    }
                    if (event.getType() == DOOR_CLOSED) {
                        smartHome.execute(lightObj -> {
                            if (!(lightObj instanceof Light)) {
                                return;
                            }

                            Light light = (Light) lightObj;
                            light.setOn(false);
                            System.out.println("Light " + light.getId() + " was turned off.");
                            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                            commandSender.sendCommand(command);
                        });
                    }

                });
            });
        }
    }
}
