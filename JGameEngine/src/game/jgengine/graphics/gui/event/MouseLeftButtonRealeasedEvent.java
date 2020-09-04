package game.jgengine.graphics.gui.event;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.gui.widgets.SmartShape;

public class MouseLeftButtonRealeasedEvent extends MouseButtonReleasedEvent
{
	public MouseLeftButtonRealeasedEvent(SmartShape<?> relativeObject)
	{
		super(relativeObject, Mouse.Button.LEFT);
	}
}
