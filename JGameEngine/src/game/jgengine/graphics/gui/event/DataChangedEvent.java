package game.jgengine.graphics.gui.event;

import game.jgengine.debug.Logs;
import game.jgengine.graphics.gui.widgets.Widget;
import game.jgengine.graphics.shapes.Shape;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DataChangedEvent extends RelativeEvent
{
	private Method getter;
	private Object oldValue;

	public DataChangedEvent(Widget<?> object, Method method)
	{
		super(object);
		this.getter = method;
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
