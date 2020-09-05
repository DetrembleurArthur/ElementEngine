package game.jgengine.components.event;

import game.jgengine.entity.GameObject;
import game.jgengine.event.Mouse;

public class MouseLeftButtonRealeasedEvent extends MouseButtonReleasedEvent
{
	public MouseLeftButtonRealeasedEvent(GameObject relativeObject)
	{
		super(relativeObject, Mouse.Button.LEFT);
	}
}
