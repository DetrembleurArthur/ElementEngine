package game.jgengine.graphics.gui.event;

import game.jgengine.graphics.shapes.Shape;

public class PositionChangedEvent extends DataChangedEvent
{
	public PositionChangedEvent(Shape object)
	{
		super(object, object.getter("getPosition2D"));
	}
}
