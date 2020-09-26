package ru.sbt.mipt.oop;

import java.io.IOException;
import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    //__________Комментарий от студента_________________
    // я понимаю, между этим методом и handlingSmartHomeEvent возникает сильный coupling, но я посчитал, что разумно отделить логику
    // симуляции от логики обработки самих событий, но не делить их по классам, тк они занимаются почти одним делом(1.5 повода для
    //изменения)))
    private void simulating(SmartHome smartHome){
        SensorEvent event = new SensorEventGenerator().getNextSensorEvent();
        while (event != null){
            handlingSmartHomeEvent(smartHome,event);
            event = new SensorEventGenerator().getNextSensorEvent();
        }
    }
    private void handlingSmartHomeEvent(SmartHome smartHome, SensorEvent event){
        System.out.println("Got event: " + event);
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            // событие от источника света
            for (Room room : smartHome.getRooms()) {
                for (Light light : room.getLights()) {
                    if (light.getId().equals(event.getObjectId())) {
                        if (event.getType() == LIGHT_ON) {
                            light.setOn(true);
                            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                        } else {
                            light.setOn(false);
                            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                        }
                    }
                }
            }
        }
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            // событие от двери
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId())) {
                        if (event.getType() == DOOR_OPEN) {
                            door.setOpen(true);
                            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                        } else {
                            door.setOpen(false);
                            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                            // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                            // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                            if (room.getName().equals("hall")) {
                                for (Room homeRoom : smartHome.getRooms()) {
                                    for (Light light : homeRoom.getLights()) {
                                        light.setOn(false);
                                        //__________Комментарий от студента_________________
                                        // вообще не понимаю смысла класса SensorCommand и отличие от SensorEvent
                                        SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                                        sendCommand(command);
                                        //
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public static void main(String... args) throws IOException {
        Application application = new Application();
        FileReader fileReader = new FileReader("smart-home-1.js");

        SmartHome smartHome = fileReader.readJSONFileForGeneratingSmartHome(SmartHome.class);
        application.simulating(smartHome);
    }
    //__________Комментарий от студента_________________
    // вообще не понимаю смысла класса SensorCommand и отличие от SensorEvent, поэтому пока этот метод не трогаю
    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }


}
