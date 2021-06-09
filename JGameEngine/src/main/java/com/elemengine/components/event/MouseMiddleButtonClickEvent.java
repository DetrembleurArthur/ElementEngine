package com.elemengine.components.event;

import com.elemengine.entity.GameObject;
import com.elemengine.event.Mouse;

public class MouseMiddleButtonClickEvent extends MouseButtonClickEvent
{
	public MouseMiddleButtonClickEvent(GameObject relativeObject, boolean repeated)
	{
		super(relativeObject, Mouse.Button.MIDDLE, repeated);
	}
}
