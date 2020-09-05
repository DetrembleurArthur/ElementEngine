package game.jgengine.components.event;

import game.jgengine.entity.GameObject;

public class PositionChangedEvent extends DataChangedEvent
{
	public PositionChangedEvent(GameObject object)
	{
		super(object, object.method("getPosition2D"));
	}
}
