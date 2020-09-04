package game.jgengine.graphics.gui.event;

import game.jgengine.graphics.gui.widgets.SmartShape;

public class FillColorChangedEvent extends DataChangedEvent
{
	public FillColorChangedEvent(SmartShape<?> object)
	{
		super(object, object.getShape().method("getFillColor"));
	}
}
