package game.jgengine.components;

import game.jgengine.entity.GameObject;
import game.jgengine.sys.Game;

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
}
