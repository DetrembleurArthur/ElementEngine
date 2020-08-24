package game.jgengine.graphics.gui.event;

import game.jgengine.graphics.gui.widgets.Widget;
import game.jgengine.graphics.shapes.Shape;

public abstract class MouseButtonEvent extends MouseEvent
{
	protected int buttonId;

	public MouseButtonEvent(Widget<?> relativeObject, int buttonId)
	{
		super(relativeObject);
		this.buttonId = buttonId;
	}

	public int getButtonId()
	{
		return buttonId;
	}
}
