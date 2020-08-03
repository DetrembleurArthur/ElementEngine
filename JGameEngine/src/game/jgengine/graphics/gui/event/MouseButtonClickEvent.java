package game.jgengine.graphics.gui.event;

import game.jgengine.event.Input;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.shapes.Shape;
import org.joml.Vector2f;

public class MouseButtonClickEvent extends MouseEvent
{
	private int buttonId;
	private boolean clickBloqued;
	private boolean repeated;

	public MouseButtonClickEvent(Shape relativeObject, int buttonId, boolean repeated)
	{
		super(relativeObject);
		this.buttonId = buttonId;
		clickBloqued = false;
		this.repeated = repeated;
	}

	@Override
	boolean isAppend()
	{
		if(Input.isButtonPressed(buttonId))
		{
			Vector2f mousePosition = Mouse.getPosition(camera);
			if(relativeObject.contains(mousePosition))
			{
				if(!clickBloqued)
				{
					clickBloqued = !repeated;
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
		}
		return false;
	}

	public int getButtonId()
	{
		return buttonId;
	}

	public boolean isClickBloqued()
	{
		return clickBloqued;
	}

	public boolean isRepeated()
	{
		return repeated;
	}
}
