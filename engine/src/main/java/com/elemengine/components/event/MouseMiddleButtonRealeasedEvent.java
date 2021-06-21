package com.elemengine.components.event;

import com.elemengine.entity.GameObject;
import com.elemengine.event.Mouse;
import com.elemengine.graphics.camera.Camera2D;

public class MouseMiddleButtonRealeasedEvent extends MouseButtonReleasedEvent
{
	public MouseMiddleButtonRealeasedEvent(GameObject relativeObject, Camera2D camera2D)
	{
		super(relativeObject, Mouse.Button.MIDDLE, camera2D);
	}
}
