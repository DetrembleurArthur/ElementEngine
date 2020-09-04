package game.jgengine.graphics.gui.event;

import game.jgengine.event.Input;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.gui.widgets.SmartShape;

public class MouseButtonReleasedEvent extends MouseButtonEvent
{
	private boolean buttonPressed;
	private boolean falseRelease;

	public MouseButtonReleasedEvent(SmartShape<?> relativeObject)
	{
		this(relativeObject, -1);
	}

	public MouseButtonReleasedEvent(SmartShape<?> relativeObject, int buttonId)
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
				if(object.getShape().contains(Mouse.getPosition(camera)))
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
