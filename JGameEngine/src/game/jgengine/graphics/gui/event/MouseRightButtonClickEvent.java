package game.jgengine.graphics.gui.event;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.gui.widgets.Widget;
import game.jgengine.graphics.shapes.Shape;

public class MouseRightButtonClickEvent extends MouseButtonClickEvent
{
	public MouseRightButtonClickEvent(Widget<?> relativeObject, boolean repeated)
	{
		super(relativeObject, Mouse.Button.RIGHT, repeated);
	}
}
