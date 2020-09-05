package game.jgengine.components.event;

import game.jgengine.entity.GameObject;
import game.jgengine.event.Mouse;

public class MouseMiddleButtonClickEvent extends MouseButtonClickEvent
{
	public MouseMiddleButtonClickEvent(GameObject relativeObject, boolean repeated)
	{
		super(relativeObject, Mouse.Button.MIDDLE, repeated);
	}
}
