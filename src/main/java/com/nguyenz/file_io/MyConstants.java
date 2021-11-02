package com.nguyenz.file_io;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MyConstants {
    private static Properties p = buildProperties();

    public static Properties buildProperties(){
        Properties properties = new Properties();
        try {
            FileReader reader=new FileReader("D:\\FresherIFI\\DemoCovidWeb\\src\\main\\resources\\constants.properties");
            properties.load(reader);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return properties;
    }
    public static String getProperty(String key){
        return p.getProperty(key);
    }
}
