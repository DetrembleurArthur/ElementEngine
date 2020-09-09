import game.jgengine.debug.Logs;
import game.jgengine.graphics.rendering.SpriteSheet;
import game.jgengine.graphics.shapes.Circle;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.graphics.shapes.Triangle;
import game.jgengine.particles.Emitter;
import game.jgengine.entity.Dynamic;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.particles.Particle;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Scene2D;
import game.jgengine.sys.Window;
import game.jgengine.utils.Colors;
import game.jgengine.utils.MathUtil;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;

import static org.joml.Math.cos;
import static org.joml.Math.sin;

public class MainScene extends Scene2D
{

	ArrayList<Dynamic> list = new ArrayList<>();

	Rectangle rect;


	@Override
	public void load()
	{
		Window.WINDOW.setClearColor(Colors.TURQUOISE);
	}


	@Override
	public void loadResources()
	{
		rect = new Rectangle();
		rect.setPosition(Window.WINDOW.getCenter());
		rect.setCenterOrigin();
		rect.setSize(300, 300);

		rect.sprites().addSpriteSheet("test", new SpriteSheet(Registry.getTexture("arrow"), 1, 3));
		rect.sprites().setCurrent("test");
		rect.sprites().setSpeedAnimation(100);

		list.add(rect);
	}

	@Override
	public void update(double dt)
	{
		getCamera2d().activateKeys(Window.WINDOW, Camera2D.SPECTATOR_KEY_SET);


		for(var o : list) o.run();

	}

	@Override
	public void render(double dt)
	{

		for(var g : list)
		{
			draw(g);
		}
	}

	@Override
	public void close()
	{

	}

	@Override
	public void closeResources()
	{
		for(var g : list)
		{
			g.destroy();
		}
	}

	@Override
	public void keyPressedEventHandler(int key)
	{
		if(key == GLFW.GLFW_KEY_LEFT_CONTROL)
		{
			Window.WINDOW
					.takeScreenShot(
					("demo_screenshots/" +
							Date.from(Instant.now()) + ".png")
					.replace(" ", "_")
					.replace(":", "-"));
		}
		else
		{


		}
	}

	@Override
	public void buttonPressedEventHandler(int button)
	{

	}
}
