package com.elemengine.components.event;

import com.elemengine.entity.GameObject;
import com.elemengine.event.Mouse;

public class MouseRightButtonRealeasedEvent extends MouseButtonReleasedEvent
{
	public MouseRightButtonRealeasedEvent(GameObject relativeObject)
	{
		super(relativeObject, Mouse.Button.RIGHT);
	}
}
