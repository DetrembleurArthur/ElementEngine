package game.jgengine.graphics.gui.event;

import game.jgengine.graphics.shapes.Shape;

import java.lang.reflect.Method;

public class FillColorChangedEvent extends DataChangedEvent
{
	public FillColorChangedEvent(Shape object)
	{
		super(object, object.getter("getFillColor"));
	}
}
