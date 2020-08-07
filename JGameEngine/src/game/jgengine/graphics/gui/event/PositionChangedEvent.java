package game.jgengine.graphics.gui.event;

import game.jgengine.entity.Transformable;
import game.jgengine.graphics.shapes.Shape;
import org.joml.Vector2f;

public class PositionChangedEvent extends ShapeChangedEvent
{
	public PositionChangedEvent(Shape object)
	{
		super(object, object.getter("getPosition2D"));
	}
}
