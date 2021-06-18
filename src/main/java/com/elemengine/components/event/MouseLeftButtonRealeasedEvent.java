package com.elemengine.components.event;

import com.elemengine.entity.GameObject;
import com.elemengine.event.Mouse;
import com.elemengine.graphics.camera.Camera2D;

public class MouseLeftButtonRealeasedEvent extends MouseButtonReleasedEvent
{
	public MouseLeftButtonRealeasedEvent(GameObject relativeObject, Camera2D camera2D)
	{
		super(relativeObject, Mouse.Button.LEFT, camera2D);
	}
}
