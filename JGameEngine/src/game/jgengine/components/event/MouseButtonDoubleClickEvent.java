package game.jgengine.components.event;

import game.jgengine.entity.GameObject;
import game.jgengine.time.StaticTimer;

public class MouseButtonDoubleClickEvent extends MouseLeftButtonClickEvent
{
	private StaticTimer timer;

	public MouseButtonDoubleClickEvent(GameObject relativeObject)
	{
		super(relativeObject, false);
		timer = new StaticTimer(200f);
	}

	@Override
	boolean isAppend()
	{
		boolean append = super.isAppend();
		if(!timer.isRunning())
		{
			if(append)
			{
				timer.activate();
			}
		}
		else
		{
			if(append)
			{
				if(!timer.isFinished())
				{
					timer.cancel();
					return true;
				}
				else
				{
					timer.cancel();
				}
			}
			else
			{
				if(timer.isFinished())
				{
					timer.cancel();
				}
			}
		}
		return false;
	}
}
