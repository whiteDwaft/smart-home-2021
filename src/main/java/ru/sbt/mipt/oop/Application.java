package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.handlers.DoorEventHandler;
import ru.sbt.mipt.oop.handlers.EventHandler;
import ru.sbt.mipt.oop.handlers.LightEventHandler;
import ru.sbt.mipt.oop.input.FileReader;
import ru.sbt.mipt.oop.input.HomeLoader;
import ru.sbt.mipt.oop.output.FileWriter;
import ru.sbt.mipt.oop.output.HomeUnloader;

import java.io.IOException;
import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    private final SensorEventGenerator sensorEventGenerator;
    private final SmartHome smartHome;
    private final EventHandler doorEventHandler;
    private final EventHandler lightEventHandler;


    public Application(HomeLoader homeLoader, HomeUnloader homeUnloader) throws IOException {
        smartHome = homeLoader.loadFromFile();
        doorEventHandler = new DoorEventHandler(smartHome);
        lightEventHandler = new LightEventHandler(smartHome);
        sensorEventGenerator = new SensorEventGeneratorImpl();
    }

    private void simulating(){
        SensorEvent event = sensorEventGenerator.getNextSensorEvent();
        while (event != null){
            System.out.println("Got event: " + event);
            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                lightEventHandler.handle(event);
            }
            if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED){
                doorEventHandler.handle(event);
            }
            event = sensorEventGenerator.getNextSensorEvent();
        }
    }

    public static void main(String... args) throws IOException {
        Application application = new Application(new FileReader("smart-home-1.js"),
                new FileWriter("smart-home-1.js"));
        application.simulating();
    }
    //__________Комментарий от студента_________________
    // вообще не понимаю смысла класса SensorCommand и отличие от SensorEvent, поэтому пока этот метод не трогаю
    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }


}
