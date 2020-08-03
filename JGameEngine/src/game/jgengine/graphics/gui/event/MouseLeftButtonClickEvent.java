package game.jgengine.graphics.gui.event;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.shapes.Shape;

public class MouseLeftButtonClickEvent extends MouseButtonClickEvent
{
	public MouseLeftButtonClickEvent(Shape relativeObject, boolean repeated)
	{
		super(relativeObject, Mouse.Button.LEFT, repeated);
	}
}
