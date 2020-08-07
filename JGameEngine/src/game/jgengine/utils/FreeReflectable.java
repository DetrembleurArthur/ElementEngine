package game.jgengine.utils;

import java.lang.reflect.Method;

public class FreeReflectable
{
	public Method getter(String name)
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
}
