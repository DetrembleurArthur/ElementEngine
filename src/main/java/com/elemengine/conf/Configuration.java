package com.elemengine.conf;

import com.elemengine.demo.DemoApp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration
{
    private static String RESOURCES_PATH;
    private static Properties properties;

    static
    {
        InputStream inputStream = DemoApp.class.getClassLoader().getResourceAsStream("conf.properties");
        properties = new Properties();
        try
        {
            properties.load(inputStream);
            RESOURCES_PATH = properties.getProperty("resources.path");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String getResourcesPath()
    {
        return RESOURCES_PATH;
    }

    public static Properties getProperties()
    {
        return properties;
    }
}
