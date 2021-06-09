package com.elemengine.components.event;

import com.elemengine.entity.GameObject;

public class FillColorChangedEvent extends DataChangedEvent
{
	public FillColorChangedEvent(GameObject object)
	{
		super(object, object.method("getFillColor"));
	}
}
