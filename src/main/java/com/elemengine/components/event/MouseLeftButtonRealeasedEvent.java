package com.elemengine.components.event;

import com.elemengine.entity.GameObject;
import com.elemengine.event.Mouse;

public class MouseLeftButtonRealeasedEvent extends MouseButtonReleasedEvent
{
	public MouseLeftButtonRealeasedEvent(GameObject relativeObject)
	{
		super(relativeObject, Mouse.Button.LEFT);
	}
}
