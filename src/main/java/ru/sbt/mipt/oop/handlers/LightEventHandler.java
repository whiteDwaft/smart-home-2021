package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.*;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventHandler  implements EventHandler {
    private final CommandSender commandSender = new CommandSenderImpl();

    @Override
    public boolean handle(SensorEvent event, SmartHome smartHome) {
        if( event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            smartHome.executeOne(event);
            SensorCommand sensorCommand;
            if (event.getType() == LIGHT_ON) {
                sensorCommand = new SensorCommand(CommandType.Light_ON, event.getObjectId());
            } else {
                sensorCommand = new SensorCommand(CommandType.LIGHT_OFF, event.getObjectId());
            }
            commandSender.sendCommand(sensorCommand);
            return true;
        }
        return false;
//        for (Room room : smartHome.getRooms()) {
//            for (Light light : room.getLights()) {
//                if (light.getId().equals(event.getObjectId())) {
//                    if (event.getType() == LIGHT_ON) {
//                        light.setOn(true);
//                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
//                    } else {
//                        light.setOn(false);
//                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
//                    }
//                }
//            }
//        }
    }
}
