package game.jgengine.graphics.gui.event;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.gui.widgets.SmartShape;

public class MouseRightButtonClickEvent extends MouseButtonClickEvent
{
	public MouseRightButtonClickEvent(SmartShape<?> relativeObject, boolean repeated)
	{
		super(relativeObject, Mouse.Button.RIGHT, repeated);
	}
}
