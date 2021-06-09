package com.elemengine.components.event;

import com.elemengine.entity.GameObject;
import com.elemengine.event.Input;
import com.elemengine.event.Mouse;
import org.joml.Vector2f;

public class MouseButtonClickEvent extends MouseButtonEvent
{
	private boolean clickBloqued;
	private boolean clicked;
	private boolean repeated;

	public MouseButtonClickEvent(GameObject relativeObject, boolean repeated)
	{
		this(relativeObject, -1, repeated);
	}

	public MouseButtonClickEvent(GameObject relativeObject, int buttonId, boolean repeated)
	{
		super(relativeObject, buttonId);
		clickBloqued = false;
		clicked = false;
		this.repeated = repeated;
	}

	@Override
	boolean isAppend()
	{
		if((buttonId == -1 && Input.isButtonPressed()) || (buttonId != -1 && Input.isButtonPressed(buttonId)))
		{
			Vector2f mousePosition = Mouse.getPosition(camera);
			if(object.contains(mousePosition) || clicked)
			{
				if(!clickBloqued)
				{
					clickBloqued = !repeated;
					clicked = true;
					return true;
				}
			}
			else
			{
				clickBloqued = true;
			}
		}
		else
		{
			if(clickBloqued)
			{
				clickBloqued = false;
			}
			clicked = false;
		}
		return false;
	}

	public boolean isClickBloqued()
	{
		return clickBloqued;
	}

	public boolean isRepeated()
	{
		return repeated;
	}

	public boolean isClicked()
	{
		return clicked;
	}
}
