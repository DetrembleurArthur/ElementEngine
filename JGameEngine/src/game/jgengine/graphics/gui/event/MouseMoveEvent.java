package game.jgengine.graphics.gui.event;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.shapes.Shape;
import org.joml.Vector2f;

public class MouseMoveEvent extends MouseEvent
{
	private Vector2f lastMousePosition;

	public MouseMoveEvent(Shape relativeObject)
	{
		super(relativeObject);
		lastMousePosition = new Vector2f();
	}

	@Override
	boolean isAppend()
	{
		Vector2f newMousePosition = Mouse.getPosition(camera);
		if(!newMousePosition.equals(lastMousePosition) && relativeObject.contains(newMousePosition))
		{
			lastMousePosition = newMousePosition;
			return true;
		}
		return false;
	}

	public Vector2f getLastMousePosition()
	{
		return lastMousePosition;
	}
}