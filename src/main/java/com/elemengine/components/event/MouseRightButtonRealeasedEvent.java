package com.elemengine.components.event;

import com.elemengine.entity.GameObject;
import com.elemengine.event.Mouse;
import com.elemengine.graphics.camera.Camera2D;

public class MouseRightButtonRealeasedEvent extends MouseButtonReleasedEvent
{
	public MouseRightButtonRealeasedEvent(GameObject relativeObject, Camera2D camera2D)
	{
		super(relativeObject, Mouse.Button.RIGHT, camera2D);
	}
}
