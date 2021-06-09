package com.elemengine.debug;

import java.util.Calendar;

public class Log
{
    public static <T> void print(T message)
    {
        System.err.println("[DEBUG] " + Calendar.getInstance().getTime() + " >> " + message);
    }
}
