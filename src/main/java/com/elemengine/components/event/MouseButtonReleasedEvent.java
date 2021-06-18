package com.elemengine.components.event;

import com.elemengine.entity.GameObject;
import com.elemengine.event.Input;
import com.elemengine.event.Mouse;
import com.elemengine.graphics.camera.Camera2D;

public class MouseButtonReleasedEvent extends MouseButtonEvent
{
	private boolean buttonPressed;
	private boolean falseRelease;

	public MouseButtonReleasedEvent(GameObject relativeObject, Camera2D camera2D)
	{
		this(relativeObject, -1, camera2D);
	}

	public MouseButtonReleasedEvent(GameObject relativeObject, int buttonId, Camera2D camera2D)
	{
		super(relativeObject, buttonId, camera2D);
		buttonPressed = false;
		falseRelease = false;
	}

	@Override
	boolean isAppend()
	{
		boolean pressed = buttonId == -1 ? Input.isButtonPressed() : Input.isButtonPressed(buttonId);
		if(falseRelease)
		{
			if(!pressed)
			{
				falseRelease = false;
				return false;
			}
		}
		else if(buttonPressed)
		{
			if(!pressed)
			{
				buttonPressed = false;
				return true;
			}
		}
		else
		{
			if(pressed)
			{
				if(object.contains(Mouse.getPosition(camera)))
				{
					buttonPressed = true;
				}
				else
				{
					falseRelease = true;
				}
			}
		}
		return false;
	}

	public boolean isButtonPressed()
	{
		return buttonPressed;
	}
}
