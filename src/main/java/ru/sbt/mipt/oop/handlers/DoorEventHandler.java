package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.*;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;


public class DoorEventHandler implements EventHandler {
    private final CommandSender commandSender = new CommandSenderImpl();


    @Override
    public void handle(SensorEvent event, SmartHome smartHome) {
        smartHome.execute(event);
        SensorCommand sensorCommand;
        if (event.getType() == DOOR_OPEN) {
            sensorCommand = new SensorCommand(CommandType.DOOR_OPEN, event.getObjectId());
        } else {
            sensorCommand = new SensorCommand(CommandType.DOOR_CLOSED, event.getObjectId());
        }
        commandSender.sendCommand(sensorCommand);
//        for (Room room : smartHome.getRooms()) {
//            for (Door door : room.getDoors()) {
//                if (door.getId().equals(event.getObjectId())) {
//                    if (event.getType() == DOOR_OPEN) {
//                        door.setOpen(true);
//                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
//                    } else {
//                        door.setOpen(false);
//                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
//                        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
//                        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
//                        if (room.getName().equals("hall")) {
//                            for (Room homeRoom : smartHome.getRooms()) {
//                                for (Light light : homeRoom.getLights()) {
//                                    light.setOn(false);
//                                    //__________Комментарий от студента_________________
//                                    // вообще не понимаю смысла класса SensorCommand и отличие от SensorEvent
//                                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
//                                    //sendCommand(command);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
    }
}
