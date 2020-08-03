package game.jgengine.graphics.gui.event;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.shapes.Shape;

public class MouseMiddleButtonClickEvent extends MouseButtonClickEvent
{
	public MouseMiddleButtonClickEvent(Shape relativeObject, boolean repeated)
	{
		super(relativeObject, Mouse.Button.MIDDLE, repeated);
	}
}
