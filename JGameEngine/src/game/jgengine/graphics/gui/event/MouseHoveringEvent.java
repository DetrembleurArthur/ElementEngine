package game.jgengine.graphics.gui.event;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.shapes.Shape;

public class MouseHoveringEvent extends MouseEvent
{
	public MouseHoveringEvent(Shape relativeObject)
	{
		super(relativeObject);
	}

	@Override
	boolean isAppend()
	{
		return object.contains(Mouse.getPosition(camera));
	}
}
