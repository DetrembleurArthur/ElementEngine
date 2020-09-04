package game.jgengine.graphics.gui.event;

import game.jgengine.graphics.gui.widgets.SmartShape;

public abstract class MouseButtonEvent extends MouseEvent
{
	protected int buttonId;

	public MouseButtonEvent(SmartShape<?> relativeObject, int buttonId)
	{
		super(relativeObject);
		this.buttonId = buttonId;
	}

	public int getButtonId()
	{
		return buttonId;
	}
}
