package com.elemengine.components.event;

import com.elemengine.entity.GameObject;
import com.elemengine.graphics.camera.Camera2D;
import com.elemengine.sys.Application;
import com.elemengine.sys.Scene2D;

public abstract class MouseEvent extends RelativeEvent
{
	protected final Camera2D camera;

	public MouseEvent(GameObject relativeObject)
	{
		super(relativeObject);
		this.camera = ((Scene2D) Application.APPLICATION.getCurrentScene()).getCamera2d();
		this.object = relativeObject;
	}

	public Camera2D getCamera()
	{
		return camera;
	}
}
