package game.jgengine.components.event;

import game.jgengine.entity.GameObject;

public class FillColorChangedEvent extends DataChangedEvent
{
	public FillColorChangedEvent(GameObject object)
	{
		super(object, object.method("getFillColor"));
	}
}
