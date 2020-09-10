package tests;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.rendering.Light;
import game.jgengine.graphics.rendering.LightRenderer;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.graphics.texts.Text;
import game.jgengine.entity.Dynamic;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Scene2D;
import game.jgengine.sys.Window;
import game.jgengine.utils.Colors;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;


public class MainScene extends Scene2D
{

	ArrayList<Dynamic> list = new ArrayList<>();

	LightRenderer renderer;
	Rectangle rect;
	Rectangle rect2;
	Rectangle b;

	@Override
	public void load()
	{
		Window.WINDOW.setClearColor(Colors.TURQUOISE);
		Rectangle.setCommonModel(true);
	}


	@Override
	public void loadResources()
	{
		renderer = new LightRenderer(getCamera2d(), new Light(Window.WINDOW.getCenter(), Colors.WHITE, 100));

		renderer.getLight().setColor(Colors.YELLOW);


		b = new Rectangle();
		b.setSize(Window.WINDOW.getSize());

		rect = new Rectangle(Registry.getTexture("ampoule"));
		rect.setSize(150, 150);
		rect.setCenterOrigin();
		rect.setPosition(new Vector3f());
		rect.events().enableMouseDragging();

		rect2 = new Rectangle(Registry.getTexture("bricks"));
		rect2.setSize(300, 300);
		rect2.setCenterOrigin();
		rect2.setPosition(Window.WINDOW.getCenter());
		rect2.events().enableMouseDragging();
		rect2.moves().setRotationSpeed(60);

		list.add(b);
		list.add(rect);
		list.add(rect2);
	}

	@Override
	public void update(double dt)
	{
		getCamera2d().activateKeys(Window.WINDOW, Camera2D.SPECTATOR_KEY_SET);

		renderer.getLight().setPosition(rect.getPosition2D());
		renderer.getLight().setRadius(renderer.getLight().getRadius()+2f);

		for(var o : list) o.run();

	}

	@Override
	public void render(double dt)
	{

		for(var g : list)
		{
			g.draw(renderer);
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
