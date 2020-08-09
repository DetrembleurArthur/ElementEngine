package game.jgengine.graphics.gui.event;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.shapes.Shape;

public class MouseEnteredEvent extends MouseEvent
{
	protected boolean entered;

	public MouseEnteredEvent(Shape relativeObject)
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
