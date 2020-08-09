package game.jgengine.graphics.gui.event;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.shapes.Shape;

public class MouseExitedEvent extends MouseEvent
{
	protected boolean exited;
	public MouseExitedEvent(Shape relativeObject)
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
