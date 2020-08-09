package game.jgengine.graphics.gui.event;

import game.jgengine.event.Input;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.shapes.Shape;

public class MouseButtonReleasedEvent extends MouseButtonEvent
{
	private boolean buttonPressed;
	private boolean falseRelease;

	public MouseButtonReleasedEvent(Shape relativeObject)
	{
		this(relativeObject, -1);
	}

	public MouseButtonReleasedEvent(Shape relativeObject, int buttonId)
	{
		super(relativeObject, buttonId);
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
