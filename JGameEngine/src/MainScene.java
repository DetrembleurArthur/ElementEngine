import game.jgengine.components.particles.Emitter;
import game.jgengine.debug.Logs;
import game.jgengine.entity.Dynamic;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.registry.Registry;
import game.jgengine.scripting.Script;
import game.jgengine.sys.Game;
import game.jgengine.sys.Scene2D;
import game.jgengine.sys.Window;
import game.jgengine.utils.Colors;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;

public class MainScene extends Scene2D
{

	ArrayList<Dynamic> list = new ArrayList<>();

	Emitter emitter;


	@Override
	public void load()
	{
		Window.WINDOW.setClearColor(Colors.BLACK);
	}


	@Override
	public void loadResources()
	{
		emitter = new Emitter(1);
		list.add(emitter);

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
