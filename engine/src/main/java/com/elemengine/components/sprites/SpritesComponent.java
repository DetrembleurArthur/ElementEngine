package com.elemengine.components.sprites;

import com.elemengine.components.Component;
import com.elemengine.debug.Log;
import com.elemengine.entity.GameObject;
import com.elemengine.graphics.rendering.SpriteSheet;
import com.elemengine.time.StaticTimer;

import java.util.HashMap;

public class SpritesComponent extends Component implements Runnable
{
	private HashMap<String, SpriteSheet> spriteSheets;
	private SpriteSheet current = null;
	private int currentId = 0;
	private StaticTimer timer;

	public SpritesComponent(GameObject relativeObject)
	{
		super(relativeObject);
		this.spriteSheets = new HashMap<>();
		this.timer = new StaticTimer(100);
		this.timer.activate();
	}

	public void addSpriteSheet(String name, SpriteSheet spriteSheet)
	{
		this.spriteSheets.put(name, spriteSheet);
	}

	public void setCurrent(String name)
	{
		current = spriteSheets.get(name);
		getRelativeObject().setTexture(current.getTexture());
		currentId = 0;
		getRelativeObject().setSprite(current.getSprite(0));
	}

	public void setCurrentId(int id)
	{
		currentId = id;
	}

	public void setSpeedAnimation(float delay)
	{
		this.timer.setMaxDelay(delay);
		this.timer.cancel();
		this.timer.activate();
	}

	@Override
	public void run()
	{
		if(timer.isFinished())
		{
			timer.activate();
			getRelativeObject().setSprite(current.getSprite(currentId));
			currentId = (currentId + 1) % current.getNbSprite();
		}
	}
}
