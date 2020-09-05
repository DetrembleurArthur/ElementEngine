package game.jgengine.components.event;

import game.jgengine.entity.GameObject;
import game.jgengine.event.Mouse;

public class MouseHoveringEvent extends MouseEvent
{
	public MouseHoveringEvent(GameObject relativeObject)
	{
		super(relativeObject);
	}

	@Override
	boolean isAppend()
	{
		return object.contains(Mouse.getPosition(camera));
	}
}
