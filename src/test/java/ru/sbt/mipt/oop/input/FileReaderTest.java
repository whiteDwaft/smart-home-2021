package ru.sbt.mipt.oop.input;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.HomeBuilder;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.output.FileWriter;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    @Test
    void loadFromFile() {
        SmartHome smartHome = new HomeBuilder(new FileWriter("smart-home-1.js")).generateSmartHomeObject();
        SmartHome smartHome1 = new FileReader("smart-home-1.js").loadFromFile();
        assertEquals(smartHome,smartHome1,"it must be equals");
    }
}