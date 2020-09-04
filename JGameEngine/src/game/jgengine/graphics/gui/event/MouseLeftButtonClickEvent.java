package game.jgengine.graphics.gui.event;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.gui.widgets.SmartShape;

public class MouseLeftButtonClickEvent extends MouseButtonClickEvent
{
	public MouseLeftButtonClickEvent(SmartShape<?> relativeObject, boolean repeated)
	{
		super(relativeObject, Mouse.Button.LEFT, repeated);
	}
}
