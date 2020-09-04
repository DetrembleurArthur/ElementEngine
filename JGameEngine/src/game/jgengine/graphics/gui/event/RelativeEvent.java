package game.jgengine.graphics.gui.event;

import game.jgengine.graphics.gui.widgets.SmartShape;

public abstract class RelativeEvent extends Event
{
	protected SmartShape<?> object;

	public RelativeEvent(SmartShape<?> object)
	{
		this.object = object;
	}

	public SmartShape<?> getObject()
	{
		return object;
	}
}
