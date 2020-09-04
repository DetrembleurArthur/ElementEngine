package game.jgengine.graphics.gui.event;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.gui.widgets.SmartShape;

public class MouseMiddleButtonClickEvent extends MouseButtonClickEvent
{
	public MouseMiddleButtonClickEvent(SmartShape<?> relativeObject, boolean repeated)
	{
		super(relativeObject, Mouse.Button.MIDDLE, repeated);
	}
}
