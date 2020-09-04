package game.jgengine.graphics.gui.event;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.gui.widgets.SmartShape;

public class MouseMiddleButtonRealeasedEvent extends MouseButtonReleasedEvent
{
	public MouseMiddleButtonRealeasedEvent(SmartShape<?> relativeObject)
	{
		super(relativeObject, Mouse.Button.MIDDLE);
	}
}
