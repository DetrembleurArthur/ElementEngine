package game.jgengine.components.event;

import game.jgengine.entity.GameObject;
import game.jgengine.event.Mouse;

public class MouseMiddleButtonRealeasedEvent extends MouseButtonReleasedEvent
{
	public MouseMiddleButtonRealeasedEvent(GameObject relativeObject)
	{
		super(relativeObject, Mouse.Button.MIDDLE);
	}
}
