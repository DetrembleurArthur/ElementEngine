package game.jgengine.graphics.gui.event;

import game.jgengine.graphics.gui.widgets.Widget;
import game.jgengine.graphics.shapes.Shape;

public abstract class RelativeEvent extends Event
{
	protected Widget<?> object;

	public RelativeEvent(Widget<?> object)
	{
		this.object = object;
	}

	public Widget<?> getObject()
	{
		return object;
	}
}
