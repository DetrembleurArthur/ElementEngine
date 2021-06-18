package com.elemengine.components.event;

import com.elemengine.entity.GameObject;
import com.elemengine.graphics.camera.Camera2D;

public abstract class MouseButtonEvent extends MouseEvent
{
	protected int buttonId;

	public MouseButtonEvent(GameObject relativeObject, int buttonId, Camera2D camera2D)
	{
		super(relativeObject, camera2D);
		this.buttonId = buttonId;
	}

	public int getButtonId()
	{
		return buttonId;
	}
}
