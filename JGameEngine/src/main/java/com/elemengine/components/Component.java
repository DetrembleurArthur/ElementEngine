package com.elemengine.components;

import com.elemengine.entity.GameObject;

public abstract class Component
{
	private GameObject relativeObject;

	public Component(GameObject relativeObject)
	{
		this.relativeObject = relativeObject;
	}

	public GameObject getRelativeObject()
	{
		return relativeObject;
	}

	public void remove()
	{
		relativeObject.removeComponent(getClass());
	}
}
