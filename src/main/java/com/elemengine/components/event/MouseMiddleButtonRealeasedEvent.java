package com.elemengine.components.event;

import com.elemengine.entity.GameObject;
import com.elemengine.event.Mouse;

public class MouseMiddleButtonRealeasedEvent extends MouseButtonReleasedEvent
{
	public MouseMiddleButtonRealeasedEvent(GameObject relativeObject)
	{
		super(relativeObject, Mouse.Button.MIDDLE);
	}
}
