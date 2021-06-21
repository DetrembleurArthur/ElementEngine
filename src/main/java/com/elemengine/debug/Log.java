package com.elemengine.debug;

import com.elemengine.conf.Configuration;

import java.util.Calendar;

public class Log
{
    public static <T> void print(T message)
    {
        if(Configuration.isEnableLogs())
            System.err.println("[DEBUG] " + Calendar.getInstance().getTime() + " >> " + message);
    }
}
