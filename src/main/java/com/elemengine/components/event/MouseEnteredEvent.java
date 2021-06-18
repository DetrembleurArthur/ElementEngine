package com.elemengine.components.event;

import com.elemengine.entity.GameObject;
import com.elemengine.event.Mouse;
import com.elemengine.graphics.camera.Camera2D;

public class MouseEnteredEvent extends MouseEvent
{
	protected boolean entered;

	public MouseEnteredEvent(GameObject relativeObject, Camera2D camera2D)
	{
		super(relativeObject, camera2D);
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
