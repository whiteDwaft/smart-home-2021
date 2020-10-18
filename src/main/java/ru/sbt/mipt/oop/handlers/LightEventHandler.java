package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.command.CommandSenderImpl;
import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.sensor.SensorEvent;

import static ru.sbt.mipt.oop.sensor.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.sensor.SensorEventType.LIGHT_ON;

public class LightEventHandler implements EventHandler {
    private final CommandSender commandSender = new CommandSenderImpl();

    @Override
    public void handle(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            smartHome.execute(lightObj -> {
                if (!(lightObj instanceof Light)) {
                    return;
                }
                Light light = (Light) lightObj;
                if (!light.getId().equals(event.getObjectId())) {
                    return;
                }
                if (event.getType() == LIGHT_ON) {
                    light.setOn(true);
                    System.out.println("Light " + light.getId() + " was turned on.");
                } else {
                    light.setOn(false);
                    System.out.println("Light " + light.getId() + " was turned off.");
                }
            });
        }
    }
}
