package com.elemengine.components.event;

import com.elemengine.entity.GameObject;
import com.elemengine.event.Mouse;
import com.elemengine.graphics.camera.Camera2D;

public class MouseLeftButtonClickEvent extends MouseButtonClickEvent
{
	public MouseLeftButtonClickEvent(GameObject relativeObject, boolean repeated, Camera2D camera2D)
	{
		super(relativeObject, Mouse.Button.LEFT, repeated, camera2D);
	}
}
