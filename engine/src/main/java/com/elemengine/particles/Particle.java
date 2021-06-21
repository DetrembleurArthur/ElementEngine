package com.elemengine.particles;

import com.elemengine.entity.Dynamic;
import com.elemengine.entity.GameObject;
import com.elemengine.graphics.rendering.Renderer;
import com.elemengine.time.StaticTimer;

public class Particle implements Dynamic
{
	private GameObject gameObject;
	private StaticTimer ttl;

	public Particle(GameObject gameObject, float ttlDelay)
	{
		this.gameObject = gameObject;
		this.ttl = new StaticTimer(ttlDelay);
		this.ttl.activate();
	}

	@Override
	public void run()
	{
		gameObject.run();
	}

	@Override
	public void destroy()
	{
		gameObject.destroy();
	}

	@Override
	public void draw()
	{
		gameObject.draw();
	}

	@Override
	public void draw(Renderer renderer)
	{
		gameObject.draw(renderer);
	}

	public boolean isDead()
	{
		return ttl.isFinished();
	}

	public boolean ifDeadDestroy()
	{
		if(isDead())
		{
			destroy();
			return true;
		}
		return false;
	}
}
