package game.jgengine.graphics.gui.event;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.gui.widgets.SmartShape;

public class MouseRightButtonRealeasedEvent extends MouseButtonReleasedEvent
{
	public MouseRightButtonRealeasedEvent(SmartShape<?> relativeObject)
	{
		super(relativeObject, Mouse.Button.RIGHT);
	}
}
