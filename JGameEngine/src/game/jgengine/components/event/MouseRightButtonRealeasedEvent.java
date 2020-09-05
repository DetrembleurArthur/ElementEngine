package game.jgengine.components.event;

import game.jgengine.entity.GameObject;
import game.jgengine.event.Mouse;

public class MouseRightButtonRealeasedEvent extends MouseButtonReleasedEvent
{
	public MouseRightButtonRealeasedEvent(GameObject relativeObject)
	{
		super(relativeObject, Mouse.Button.RIGHT);
	}
}
