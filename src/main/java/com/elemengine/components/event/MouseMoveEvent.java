package com.elemengine.components.event;

import com.elemengine.entity.GameObject;
import com.elemengine.event.Mouse;
import com.elemengine.graphics.camera.Camera2D;
import org.joml.Vector2f;

public class MouseMoveEvent extends MouseEvent
{
	private Vector2f lastMousePosition;

	public MouseMoveEvent(GameObject relativeObject, Camera2D camera2D)
	{
		super(relativeObject, camera2D);
		lastMousePosition = new Vector2f();
	}

	@Override
	boolean isAppend()
	{
		Vector2f newMousePosition = Mouse.getPosition(camera);
		if(!newMousePosition.equals(lastMousePosition) && object.contains(newMousePosition))
		{
			lastMousePosition = newMousePosition;
			return true;
		}
		return false;
	}

	public Vector2f getLastMousePosition()
	{
		return lastMousePosition;
	}
}
