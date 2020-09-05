package game.jgengine.components.event;

import game.jgengine.entity.GameObject;
import game.jgengine.event.Mouse;
import org.joml.Vector2f;

public class MouseMoveEvent extends MouseEvent
{
	private Vector2f lastMousePosition;

	public MouseMoveEvent(GameObject relativeObject)
	{
		super(relativeObject);
		lastMousePosition = new Vector2f();
	}

	@Override
	boolean isAppend()
	{
		Vector2f newMousePosition = Mouse.getPosition(camera);
		if(!newMousePosition.equals(lastMousePosition) && object.contains(newMousePosition))
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
