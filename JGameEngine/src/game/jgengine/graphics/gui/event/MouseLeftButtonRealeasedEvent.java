package game.jgengine.graphics.gui.event;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.gui.widgets.Widget;
import game.jgengine.graphics.shapes.Shape;

public class MouseLeftButtonRealeasedEvent extends MouseButtonReleasedEvent
{
	public MouseLeftButtonRealeasedEvent(Widget<?> relativeObject)
	{
		super(relativeObject, Mouse.Button.LEFT);
	}
}
