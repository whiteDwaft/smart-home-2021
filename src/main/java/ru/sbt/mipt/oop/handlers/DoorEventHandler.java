package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.command.CommandSenderImpl;
import ru.sbt.mipt.oop.elements.Door;
import ru.sbt.mipt.oop.sensor.SensorEvent;

import static ru.sbt.mipt.oop.sensor.SensorEventType.*;


public class DoorEventHandler implements EventHandler {
    private final CommandSender commandSender = new CommandSenderImpl();


    @Override
    public void handle(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            smartHome.execute(doorObj -> {
                if (!(doorObj instanceof Door)) {
                    return;
                }
                Door door = (Door) doorObj;
                if (!door.getId().equals(event.getObjectId())) {
                    return;
                }
                if (event.getType() == DOOR_OPEN) {
                    door.setOpen(true);
                    System.out.println("Door " + door.getId() + " was opened.");
                } else {
                    door.setOpen(false);
                    System.out.println("Door " + door.getId() + " was closed.");
                }
            });
        }
    }
}
