package game.jgengine.components.event;

import game.jgengine.entity.GameObject;
import game.jgengine.event.Mouse;

public class MouseEnteredEvent extends MouseEvent
{
	protected boolean entered;

	public MouseEnteredEvent(GameObject relativeObject)
	{
		super(relativeObject);
		entered = false;
	}

	@Override
	boolean isAppend()
	{
		boolean intersects = object.contains(Mouse.getPosition(camera));
		if(intersects)
		{
			if(!entered)
			{
				entered = true;
				return true;
			}
		}
		else
		{
			if(entered)
			{
				entered = false;
			}
		}
		return false;
	}

	public boolean isEntered()
	{
		return entered;
	}
}
