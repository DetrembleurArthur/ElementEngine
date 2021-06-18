package com.elemengine.components.event;

import com.elemengine.entity.GameObject;
import com.elemengine.event.Mouse;
import com.elemengine.graphics.camera.Camera2D;

public class MouseHoveringEvent extends MouseEvent
{
	public MouseHoveringEvent(GameObject relativeObject, Camera2D camera2D)
	{
		super(relativeObject, camera2D);
	}

	@Override
	boolean isAppend()
	{
		return object.contains(Mouse.getPosition(camera));
	}
}
