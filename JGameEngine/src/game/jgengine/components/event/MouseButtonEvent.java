package game.jgengine.components.event;

import game.jgengine.entity.GameObject;

public abstract class MouseButtonEvent extends MouseEvent
{
	protected int buttonId;

	public MouseButtonEvent(GameObject relativeObject, int buttonId)
	{
		super(relativeObject);
		this.buttonId = buttonId;
	}

	public int getButtonId()
	{
		return buttonId;
	}
}