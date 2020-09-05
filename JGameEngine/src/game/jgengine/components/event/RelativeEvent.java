package game.jgengine.components.event;

import game.jgengine.entity.GameObject;

public abstract class RelativeEvent extends Event
{
	protected GameObject object;

	public RelativeEvent(GameObject object)
	{
		this.object = object;
	}

	public GameObject getObject()
	{
		return object;
	}
}
