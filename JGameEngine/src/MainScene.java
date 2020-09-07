import game.jgengine.entity.Dynamic;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.scripting.Script;
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

	Rectangle rect;


	@Override
	public void load()
	{
		Window.WINDOW.setClearColor(Colors.BLACK);
	}


	@Override
	public void loadResources()
	{
		rect = new Rectangle();
		rect.setSize(150, 150);
		rect.setFillColor(Colors.WHITE);
		rect.setPosition(Window.WINDOW.getCenter());
		rect.setCenterOrigin();


		float height = Window.WINDOW.getSize().y;

		rect.moves().setRotationSpeed(600);
		rect.moves().setRotationAcceleration(-20);



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
		var component = rect.getVectorComponent(Mouse.getPosition(getCamera2d()));
	}
}
