package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.sbt.mipt.oop.api.ApiAdapter;
import ru.sbt.mipt.oop.handlers.*;

import ru.sbt.mipt.oop.input.FileReader;
import ru.sbt.mipt.oop.input.HomeLoader;
import ru.sbt.mipt.oop.rc.RemoteControl;
import ru.sbt.mipt.oop.rc.RemoteControl1;
import ru.sbt.mipt.oop.rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.rccommands.*;
import ru.sbt.mipt.oop.sensor.SensorEventType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@PropertySource("application.properties")
public class AppConfiguration {

    @Bean
    public SensorEventsManager getSensorEventsManager(List<EventHandler> eventHandler,
                                                      SmartHome smartHome,
                                                      Map<String, SensorEventType> convertor) {

        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(new ApiAdapter(
                new AlarmDecorator(eventHandler),
                smartHome,
                convertor
        ));
        return sensorEventsManager;

    }

    @Bean
    public SmartHome getSmartHome(HomeLoader homeLoader) {
        SmartHome smartHome = homeLoader.loadFromFile();
        smartHome.formSignalizationObj();
        return smartHome;
    }

    @Bean
    public HomeLoader getHomeLoader(@Value("${filename}") String fileName) {
        return new FileReader(fileName);
    }

    @Bean
    public Map<String, SensorEventType> getConvertor() {
        return new HashMap<String, SensorEventType>() {{
            put("LightIsOn", SensorEventType.LIGHT_ON);
            put("LightIsOff", SensorEventType.LIGHT_OFF);
            put("DoorIsOpen", SensorEventType.DOOR_OPEN);
            put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        }};
    }

    @Bean
    EventHandler getDoorEventHandler() {
        return new DoorEventHandler();
    }

    @Bean
    EventHandler getLightEventHandler() {
        return new LightEventHandler();
    }

    @Bean
    EventHandler getSignalizationEventHandler() {
        return new SignalizationEventHandler();
    }

    @Bean
    EventHandler getHallDoorEventHandler() {
        return new HallDoorEventHandler();
    }

    @Bean
    RCCommand getActivateSignalizationCommand(SmartHome smartHome) {
        return new ActivateSignalizationCommand(smartHome);
    }

    @Bean
    RCCommand getAlarmOnCommand(SmartHome smartHome) {
        return new AlarmOnCommand(smartHome);
    }

    @Bean
    RCCommand getCloseHallDoorCommand(SmartHome smartHome) {
        return new CloseHallDoorCommand(smartHome);
    }

    @Bean
    RCCommand getSwitchOffAllLightCommand(SmartHome smartHome) {
        return new SwitchOffAllLightCommand(smartHome);
    }

    @Bean
    RCCommand getSwitchOffHallLightCommand(SmartHome smartHome) {
        return new SwitchOffHallLightCommand(smartHome);
    }

    @Bean
    RCCommand getSwitchOnAllLightCommand(SmartHome smartHome) {
        return new SwitchOnAllLightCommand(smartHome);
    }

    @Bean
    Map<String, RCCommand> getrcDescriptor(
            RCCommand ActivateSignalizationCommand,
            RCCommand AlarmOnCommand,
            RCCommand CloseHallDoorCommand,
            RCCommand SwitchOffAllLightCommand,
            RCCommand SwitchOffHallLightCommand,
            RCCommand SwitchOnAllLightCommand
    ) {
        Map<String, RCCommand> rcDescriptor = new HashMap<>();
        rcDescriptor.put("A", ActivateSignalizationCommand);
        rcDescriptor.put("B", AlarmOnCommand);
        rcDescriptor.put("C", CloseHallDoorCommand);
        rcDescriptor.put("D", SwitchOffAllLightCommand);
        rcDescriptor.put("1", SwitchOffHallLightCommand);
        rcDescriptor.put("2", SwitchOnAllLightCommand);
        return rcDescriptor;
    }

    @Bean
    RemoteControl getRemoteControl(Map<String, RCCommand> rcDescriptor,String rcid){
        return new RemoteControl1(rcDescriptor, rcid);
    }

    @Bean
    RemoteControlRegistry getRemoteControlRegistry(List<RemoteControl1> remoteControlList){
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlList.forEach(remoteControl -> remoteControlRegistry.registerRemoteControl(remoteControl,remoteControl.getRcid()));
        return remoteControlRegistry;
    }
}
