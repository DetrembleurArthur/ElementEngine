package com.elemengine.particles;

import com.elemengine.entity.Dynamic;
import com.elemengine.graphics.rendering.Renderer;
import com.elemengine.time.StaticTimer;
import org.joml.Vector2f;

import java.util.ArrayList;

public abstract class Emitter implements Dynamic
{
	private final ArrayList<Particle> particles;
	private Vector2f position;
	private final StaticTimer delayBP;

	@Override
	public void destroy()
	{
		for(var p : particles)
			p.destroy();
	}

	@Override
	public void draw()
	{
		for(var p : particles)
			p.draw();
	}

	@Override
	public void draw(Renderer renderer)
	{
		for(var p : particles)
			p.draw(renderer);
	}


	public Emitter(Vector2f position, float delayBetweenParticles)
	{
		particles = new ArrayList<>();
		this.position = position;
		this.delayBP = new StaticTimer(delayBetweenParticles);
		this.delayBP.activate();
	}

	public Emitter()
	{
		this(new Vector2f(), 0f);
	}

	protected void addParticle(Particle particle)
	{
		particles.add(particle);
	}

	public abstract void emit();

	@Override
	public void run()
	{
		particles.removeIf(Particle::ifDeadDestroy);
		for(Particle particle : particles)
		{
			particle.run();
		}
		if(delayBP.isFinished())
		{
			emit();
			delayBP.activate();
		}
	}

	public Vector2f getPosition()
	{
		return position;
	}

	public void setPosition(Vector2f position)
	{
		this.position = position;
	}

	public void setParticleDelay(float delay)
	{
		this.delayBP.setMaxDelay(delay);
		this.delayBP.cancel();
		this.delayBP.activate();
	}
}
