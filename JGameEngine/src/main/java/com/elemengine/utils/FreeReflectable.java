package com.elemengine.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class FreeReflectable
{
	public Method method(String name)
	{
		try
		{
			return getClass().getMethod(name);
		} catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public Field field(String name)
	{
		try
		{
			return getClass().getField(name);
		} catch (NoSuchFieldException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
