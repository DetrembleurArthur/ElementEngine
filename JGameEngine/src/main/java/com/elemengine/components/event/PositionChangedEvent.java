package com.elemengine.components.event;

import com.elemengine.entity.GameObject;

public class PositionChangedEvent extends DataChangedEvent
{
	public PositionChangedEvent(GameObject object)
	{
		super(object, object.method("getPosition2D"));
	}
}
