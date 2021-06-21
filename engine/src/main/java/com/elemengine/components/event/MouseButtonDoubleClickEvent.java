package com.elemengine.components.event;

import com.elemengine.entity.GameObject;
import com.elemengine.graphics.camera.Camera2D;
import com.elemengine.time.StaticTimer;

public class MouseButtonDoubleClickEvent extends MouseLeftButtonClickEvent
{
	private StaticTimer timer;

	public MouseButtonDoubleClickEvent(GameObject relativeObject, Camera2D camera2D)
	{
		super(relativeObject, false, camera2D);
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
