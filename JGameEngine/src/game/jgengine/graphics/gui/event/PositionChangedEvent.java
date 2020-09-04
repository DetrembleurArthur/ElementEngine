package game.jgengine.graphics.gui.event;

import game.jgengine.graphics.gui.widgets.SmartShape;

public class PositionChangedEvent extends DataChangedEvent
{
	public PositionChangedEvent(SmartShape<?> object)
	{
		super(object, object.getShape().method("getPosition2D"));
	}
}
