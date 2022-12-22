package com.coherent.training.selenium.stanila.base.utils;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadFile {

    @SneakyThrows
    public static String read(String name){
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/data.properties"));
        String dataNeeded = properties.getProperty(name);
        return dataNeeded;
    }

    @SneakyThrows
    public static String readClassified(String name){
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/classified.properties"));
        String dataNeeded = properties.getProperty(name);
        return dataNeeded;
    }
}
