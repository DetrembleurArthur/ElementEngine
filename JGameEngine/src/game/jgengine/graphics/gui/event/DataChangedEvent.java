package game.jgengine.graphics.gui.event;

import game.jgengine.debug.Logs;
import game.jgengine.graphics.shapes.Shape;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DataChangedEvent extends Event
{
	private Shape object;
	private Method getter;
	private Object oldValue;

	public DataChangedEvent(Shape object, Method method)
	{
		this.object = object;
		this.getter = method;
	}

	public Shape getObject()
	{
		return object;
	}

	@Override
	boolean isAppend()
	{
		try
		{
			Object obj = getter.invoke(object);
			if(!obj.equals(oldValue))
			{
				oldValue = obj;
				return true;
			}
		} catch (IllegalAccessException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
