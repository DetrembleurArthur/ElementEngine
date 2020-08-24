package game.jgengine.graphics.gui.event;

import game.jgengine.graphics.gui.widgets.Widget;
import game.jgengine.graphics.shapes.Shape;

public class FillColorChangedEvent extends DataChangedEvent
{
	public FillColorChangedEvent(Widget<?> object)
	{
		super(object, object.getShape().method("getFillColor"));
	}
}
