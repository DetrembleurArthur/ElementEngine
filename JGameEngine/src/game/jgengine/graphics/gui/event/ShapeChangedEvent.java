package game.jgengine.graphics.gui.event;

import game.jgengine.graphics.shapes.Shape;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ShapeChangedEvent extends Event
{
	private Shape object;
	private Method getter;
	private Object oldValue;

	public ShapeChangedEvent(Shape object, Method method)
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
