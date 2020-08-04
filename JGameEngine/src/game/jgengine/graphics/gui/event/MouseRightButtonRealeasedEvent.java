package game.jgengine.graphics.gui.event;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.shapes.Shape;

public class MouseRightButtonRealeasedEvent extends MouseButtonReleasedEvent
{
	public MouseRightButtonRealeasedEvent(Shape relativeObject)
	{
		super(relativeObject, Mouse.Button.RIGHT);
	}
}
