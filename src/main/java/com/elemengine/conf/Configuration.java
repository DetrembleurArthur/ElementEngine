package com.elemengine.conf;

import com.elemengine.demo.DemoApp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration
{
    private static String ENGINE_ASSETS_PATH;
    private static String ASSETS_PATH;
    private static boolean ENABLE_LOGS;
    private static Properties properties;

    static
    {
        InputStream inputStream = DemoApp.class.getClassLoader().getResourceAsStream("conf.properties");
        properties = new Properties();
        try
        {
            properties.load(inputStream);
            ENGINE_ASSETS_PATH = properties.getProperty("engine.assets.path");
            ASSETS_PATH = properties.getProperty("assets.path");
            ENABLE_LOGS = properties.getProperty("enable.logs").equals("true");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String getEngineAssetsPath()
    {
        return ENGINE_ASSETS_PATH;
    }

    public static String getAssetsPath()
    {
        return ASSETS_PATH;
    }

    public static boolean isEnableLogs()
    {
        return ENABLE_LOGS;
    }

    public static Properties getProperties()
    {
        return properties;
    }
}
