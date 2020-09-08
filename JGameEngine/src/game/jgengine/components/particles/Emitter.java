package game.jgengine.components.particles;

import game.jgengine.components.Component;
import game.jgengine.debug.Logs;
import game.jgengine.entity.Dynamic;
import game.jgengine.entity.GameObject;
import game.jgengine.graphics.rendering.Renderer;
import game.jgengine.graphics.shapes.Circle;
import game.jgengine.scripting.Script;
import game.jgengine.sys.Window;
import game.jgengine.time.StaticTimer;
import game.jgengine.utils.MathUtil;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.Random;

public class Emitter implements Dynamic
{
	private ArrayList<Particle> particles;
	private int maxParticles;

	@Override
	public void destroy()
	{
		for(var p : particles)
			p.gameObject.destroy();
	}

	@Override
	public void draw()
	{
		for(var p : particles)
			p.gameObject.draw();
	}

	@Override
	public void draw(Renderer renderer)
	{
		for(var p : particles)
			p.gameObject.draw(renderer);
	}

	private static class Particle
	{
		GameObject gameObject;
		private StaticTimer ttl;

		public Particle(GameObject gameObject, float delay)
		{
			this.gameObject = gameObject;
			ttl = new StaticTimer(delay);
			ttl.activate();
		}

		boolean isDead()
		{
			return ttl.isFinished();
		}

		boolean ifDeadDelete()
		{
			if(isDead())
			{
				gameObject.destroy();
				gameObject = null;
				return true;
			}
			return false;
		}
	}

	public Emitter(int maxParticles)
	{
		particles = new ArrayList<>();
		this.maxParticles = maxParticles;
	}

	private void emit()
	{
		int n = maxParticles;
		for(int i = 0; i < n; i++)
		{
			Vector2f pos = new Vector2f(MathUtil.rand(1000, -1000), MathUtil.rand(1000, -1000));
			Circle c = new Circle(15, 30, null);
			c.setCenterOrigin();
			c.setPosition(Window.WINDOW.getCenter());
			Vector2f t = c.getVectorComponent(pos, 300);
			c.moves().setSpeed(t);
			particles.add(new Particle(c, 2000));
		}
	}

	@Override
	public void run()
	{
		particles.removeIf(Particle::ifDeadDelete);
		for(Particle particle : particles)
		{
			particle.gameObject.run();
		}
		emit();
	}
}
