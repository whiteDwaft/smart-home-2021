package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import ru.sbt.mipt.oop.api.ApiAdapter;
import ru.sbt.mipt.oop.api.Conventor;
import ru.sbt.mipt.oop.handlers.*;

import ru.sbt.mipt.oop.input.FileReader;
import ru.sbt.mipt.oop.input.HomeLoader;
import ru.sbt.mipt.oop.sensor.SensorEventType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@PropertySource("application.properties")
public class AppConfiguration {

    @Bean
    public SensorEventsManager SensorEventsManager(List<EventHandler> eventHandler,
                                                      SmartHome smartHome,
                                                      Conventor convertor){

        smartHome.formSignalizationObj();
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(new ApiAdapter(
                new AlarmDecorator(eventHandler),
                smartHome,
                convertor
        ));
        return sensorEventsManager;
    }

    @Bean
    public SmartHome SmartHome(HomeLoader homeLoader) {
        return homeLoader.loadFromFile();
    }

    @Bean
    public HomeLoader HomeLoader(@Value("${filename}") String fileName){
        return new FileReader(fileName);
    }

    @Bean
    public Conventor Convertor(){
        return new Conventor(new HashMap<String, SensorEventType>() {{
            put("LightIsOn", SensorEventType.LIGHT_ON);
            put("LightIsOff", SensorEventType.LIGHT_OFF);
            put("DoorIsOpen", SensorEventType.DOOR_OPEN);
            put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        }});
    }
    @Bean
    EventHandler DoorEventHandler() {
        return new DoorEventHandler();
    }

    @Bean
    EventHandler LightEventHandler() {
        return new LightEventHandler();
    }

    @Bean
    EventHandler SignalizationEventHandler() {
        return new SignalizationEventHandler();
    }

    @Bean
    EventHandler HallDoorEventHandler(){
        return new HallDoorEventHandler();
    }
}
