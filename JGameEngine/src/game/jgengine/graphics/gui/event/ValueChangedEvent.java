package game.jgengine.graphics.gui.event;

import game.jgengine.debug.Logs;
import game.jgengine.graphics.shapes.Shape;

public class ValueChangedEvent extends Event
{
	private Object oldValue;
	private Valuable valuable;

	public ValueChangedEvent(Valuable valuable, Object value)
	{
		oldValue = value;
		this.valuable = valuable;
	}

	@Override
	boolean isAppend()
	{
		Object value = valuable.getValue();
		if(!oldValue.equals(value))
		{
			oldValue = value;
			return true;
		}
		return false;
	}

	public Object getOldValue()
	{
		return oldValue;
	}

	public Valuable getValuable()
	{
		return valuable;
	}
}
