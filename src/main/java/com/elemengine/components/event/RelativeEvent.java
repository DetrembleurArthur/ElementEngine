package com.elemengine.components.event;

import com.elemengine.entity.GameObject;

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
