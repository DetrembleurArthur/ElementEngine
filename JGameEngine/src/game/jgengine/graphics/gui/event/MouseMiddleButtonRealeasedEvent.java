package game.jgengine.graphics.gui.event;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.shapes.Shape;

public class MouseMiddleButtonRealeasedEvent extends MouseButtonReleasedEvent
{
	public MouseMiddleButtonRealeasedEvent(Shape relativeObject)
	{
		super(relativeObject, Mouse.Button.MIDDLE);
	}
}
