package tests;

import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.entity.Dynamic;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Scene2D;
import game.jgengine.sys.Window;
import game.jgengine.utils.Colors;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;


public class MainScene extends Scene2D
{

	ArrayList<Dynamic> list = new ArrayList<>();

	Rectangle rect;
	Rectangle rect2;

	@Override
	public void load()
	{
		Window.WINDOW.setClearColor(Colors.TURQUOISE);
		Rectangle.setCommonModel(true);
	}


	@Override
	public void loadResources()
	{






		rect = new Rectangle(Registry.getTexture("ampoule"));
		rect.setSize(150, 150);
		rect.setCenterOrigin();
		rect.setPosition(new Vector3f());
		rect.setFillColor(new Vector4f(2, 2, 2, 1));
		rect.events().enableMouseDragging();

		rect2 = new Rectangle(Registry.getTexture("bricks"));
		rect2.setSize(300, 300);
		rect2.setCenterOrigin();
		rect2.setPosition(Window.WINDOW.getCenter());
		rect2.events().enableMouseDragging();
		rect2.moves().setRotationSpeed(60);

		list.add(rect);
		list.add(rect2);
	}

	@Override
	public void update(double dt)
	{
		getCamera2d().activateKeys(Window.WINDOW, Camera2D.SPECTATOR_KEY_SET);


		//renderer.getLight().setRadius(renderer.getLight().getRadius()+2f);

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
