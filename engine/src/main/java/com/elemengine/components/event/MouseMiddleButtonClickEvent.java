package com.elemengine.components.event;

import com.elemengine.entity.GameObject;
import com.elemengine.event.Mouse;
import com.elemengine.graphics.camera.Camera2D;

public class MouseMiddleButtonClickEvent extends MouseButtonClickEvent
{
	public MouseMiddleButtonClickEvent(GameObject relativeObject, boolean repeated, Camera2D camera2D)
	{
		super(relativeObject, Mouse.Button.MIDDLE, repeated, camera2D);
	}
}
