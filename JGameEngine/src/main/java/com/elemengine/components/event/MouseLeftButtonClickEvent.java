package com.elemengine.components.event;

import com.elemengine.entity.GameObject;
import com.elemengine.event.Mouse;

public class MouseLeftButtonClickEvent extends MouseButtonClickEvent
{
	public MouseLeftButtonClickEvent(GameObject relativeObject, boolean repeated)
	{
		super(relativeObject, Mouse.Button.LEFT, repeated);
	}
}
