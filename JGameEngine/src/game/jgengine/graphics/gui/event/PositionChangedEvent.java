package game.jgengine.graphics.gui.event;

import game.jgengine.graphics.gui.widgets.Widget;
import game.jgengine.graphics.shapes.Shape;

public class PositionChangedEvent extends DataChangedEvent
{
	public PositionChangedEvent(Widget<?> object)
	{
		super(object, object.getShape().method("getPosition2D"));
	}
}
