package game.jgengine.graphics.gui.event;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.gui.widgets.Widget;
import game.jgengine.graphics.shapes.Shape;

public class MouseHoveringEvent extends MouseEvent
{
	public MouseHoveringEvent(Widget<?> relativeObject)
	{
		super(relativeObject);
	}

	@Override
	boolean isAppend()
	{
		return object.getShape().contains(Mouse.getPosition(camera));
	}
}
