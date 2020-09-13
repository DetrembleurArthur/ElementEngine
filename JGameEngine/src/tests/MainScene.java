package tests;

import game.jgengine.entity.Dynamic;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.rendering.Light;
import game.jgengine.graphics.rendering.LightRenderer;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.graphics.shapes.Triangle;
import game.jgengine.particles.Emitter;
import game.jgengine.particles.Particle;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Scene2D;
import game.jgengine.sys.Window;
import game.jgengine.tweening.TFunc;
import game.jgengine.tweening.TimedTweenAction;
import game.jgengine.utils.Colors;
import game.jgengine.utils.LaterList;
import game.jgengine.utils.MathUtil;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import java.sql.Date;
import java.time.Instant;


public class MainScene extends Scene2D
{

	LaterList<Dynamic> list = new LaterList<>();

	Emitter emitter;
	LightRenderer renderer;


	@Override
	public void load()
	{
		Window.WINDOW.setClearColor(Colors.BLACK);
		Rectangle.setCommonModel(true);
	}


	@Override
	public void loadResources()
	{
		renderer = new LightRenderer(getCamera2d(), new Light(Window.WINDOW.getCenter(), Colors.WHITE, 500));
		emitter = new Emitter()
		{
			@Override
			public void emit()
			{
				Triangle triangle = new Triangle(null);
				triangle.setSize(20, 20);
				triangle.setFillColor(Colors.random());
				triangle.setCenterOrigin();
				triangle.events().enableMouseDragging();
				triangle.animations().create("test")
						.toSize(triangle.getSize2D(), new Vector2f(30, 30), TFunc.EASE_IN_OUT_QUAD, 300, TimedTweenAction.INFINITE_CYCLE, true);
				triangle.animations().startAnimation("test");
				triangle.moves().setRotationSpeed(MathUtil.rand(360, -360));
				triangle.moves().setSpeed(new Vector2f(MathUtil.rand(-500, 500), MathUtil.rand(-500, 500)));
				triangle.setPosition(emitter.getPosition());
				Particle particle = new Particle(triangle, 3000);
				addParticle(particle);
			}
		};
		emitter.setPosition(Window.WINDOW.getCenter());


		list.addLater(emitter);
	}

	@Override
	public void update(double dt)
	{
		getCamera2d().activateKeys(Window.WINDOW, Camera2D.SPECTATOR_KEY_SET);

		list.run();

	}

	@Override
	public void render(double dt)
	{

		list.draw(renderer);
	}

	@Override
	public void close()
	{

	}

	@Override
	public void closeResources()
	{
		list.destroy();
	}



	@Override
	public void keyPressedEventHandler(int key)
	{
		if(key == GLFW.GLFW_KEY_LEFT_CONTROL)
		{
			Window.WINDOW.takeScreenShot();
		}
		else
		{




		}
	}

	@Override
	public void buttonPressedEventHandler(int button)
	{
		if(button == Mouse.Button.RIGHT)
			getCamera2d().focus(Mouse.getPosition(getCamera2d()));
	}
}
