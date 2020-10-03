package ru.sbt.mipt.oop.input;

import ru.sbt.mipt.oop.SmartHome;

import java.io.IOException;

public interface HomeLoader {

    SmartHome loadFromFile() throws IOException;
}
