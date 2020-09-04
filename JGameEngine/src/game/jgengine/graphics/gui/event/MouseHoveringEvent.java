package game.jgengine.graphics.gui.event;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.gui.widgets.SmartShape;

public class MouseHoveringEvent extends MouseEvent
{
	public MouseHoveringEvent(SmartShape<?> relativeObject)
	{
		super(relativeObject);
	}

	@Override
	boolean isAppend()
	{
		return object.getShape().contains(Mouse.getPosition(camera));
	}
}
