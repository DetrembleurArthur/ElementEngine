package game.jgengine.components.event;

import game.jgengine.entity.GameObject;
import game.jgengine.event.Mouse;

public class MouseExitedEvent extends MouseEvent
{
	protected boolean exited;
	public MouseExitedEvent(GameObject relativeObject)
	{
		super(relativeObject);
		exited = true;
	}

	@Override
	boolean isAppend()
	{
		boolean intersects = object.contains(Mouse.getPosition(camera));
		if(intersects)
		{
			if(exited)
			{
				exited = false;
			}
		}
		else
		{
			if(!exited)
			{
				exited = true;
				return true;
			}
		}
		return false;
	}

	public boolean isExited()
	{
		return exited;
	}
}
