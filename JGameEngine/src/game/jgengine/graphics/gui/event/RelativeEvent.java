package game.jgengine.graphics.gui.event;

import game.jgengine.graphics.shapes.Shape;

public abstract class RelativeEvent extends Event
{
	protected Shape object;

	public RelativeEvent(Shape object)
	{
		this.object = object;
	}

	public Shape getObject()
	{
		return object;
	}
}
