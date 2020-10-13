package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.*;

public class HallDoorEventHandler implements EventHandler {
    private final CommandSender commandSender;
    private final SensorEvent newEvent;

    public HallDoorEventHandler() {
        this.newEvent = new SensorEvent(SensorEventType.LIGHT_OFF, null);
        this.commandSender = new CommandSenderImpl();
        ;
    }

    @Override
    public void handle(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == SensorEventType.DOOR_CLOSED) {
            for (Room room : smartHome.getRooms()) {
                if (room.getName().equals("hall") &&
                        room.getDoors().stream().anyMatch(it -> it.getId().equals(event.getObjectId()))) {
                    smartHome.executeAll(newEvent); // метод для hw2 (но я его реализовал только в hw3) => см hw3
                }
            }
        }
    }
}
