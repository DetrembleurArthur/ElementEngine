package com.elemengine.components.event;

import com.elemengine.entity.GameObject;
import com.elemengine.event.Mouse;

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
