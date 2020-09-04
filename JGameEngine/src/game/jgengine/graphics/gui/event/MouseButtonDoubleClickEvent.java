package game.jgengine.graphics.gui.event;

import game.jgengine.graphics.gui.widgets.SmartShape;
import game.jgengine.time.StaticTimer;

public class MouseButtonDoubleClickEvent extends MouseLeftButtonClickEvent
{
	private StaticTimer timer;

	public MouseButtonDoubleClickEvent(SmartShape<?> relativeObject)
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
