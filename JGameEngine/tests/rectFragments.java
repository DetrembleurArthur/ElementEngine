package tests;

import game.jgengine.debug.Logs;
import game.jgengine.entity.GraphicElement;
import game.jgengine.event.Input;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.rendering.Light;
import game.jgengine.graphics.rendering.LightRenderer;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.entity.Dynamic;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Scene2D;
import game.jgengine.sys.Window;
import game.jgengine.utils.Colors;
import game.jgengine.utils.LaterList;
import game.jgengine.utils.MathUtil;
import org.joml.Math;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;


public class MainScene extends Scene2D
{

	LaterList<Dynamic> list = new LaterList<>();
	LightRenderer renderer;

	Rectangle rect;

	@Override
	public void load()
	{
		Window.WINDOW.setClearColor(Colors.BLACK);
		Rectangle.setCommonModel(true);
	}


	@Override
	public void loadResources()
	{
		renderer = new LightRenderer(getCamera2d(), new Light(Window.WINDOW.getCenter(), new Vector4f(2), 500));
		renderer.getLight().setBasicPower(new Vector4f(0.05f, 0.05f, 0.05f, 1f));






		rect = new Rectangle(Registry.getTexture("bricks"));
		rect.setSize(300, 300);
		rect.setCenterOrigin();
		rect.setPosition(Window.WINDOW.getCenter());
		rect.events().enableMouseDragging();
		rect.events().onMouseLeftButtonClicked(sender -> {
				var fragments = rect.getFragments(4, 4);
				for(var t : fragments)
				{
					t.events().enableMouseDragging();
					t.moves().setSpeedZeroCondition(true);
					t.moves().setRotationSpeedZeroCondition(true );
					t.moves().setRotationSpeed(MathUtil.rand(6000, -600));
					var dec = -MathUtil.rand(0.006f, 0.001f);
					t.moves().setRotationAcceleration(t.moves().getRotationSpeed() * dec);
					t.moves().setSpeed(new Vector2f(MathUtil.rand(5000, -5000), MathUtil.rand(5000, -5000)));
					t.moves().setAcceleration(new Vector2f(t.moves().getSpeed()).mul(dec));
				}
				rect.destroy();
				list.addLater(fragments);
				list.removeLater(rect);
		}, false);

		list.add(rect);
	}

	@Override
	public void update(double dt)
	{
		getCamera2d().activateKeys(Window.WINDOW, Camera2D.SPECTATOR_KEY_SET);
		if(Input.isKeyPressed(Window.WINDOW, GLFW.GLFW_KEY_RIGHT_SHIFT))
		{
			getCamera2d().getZoom().mul(1.1f);
		}
		else if(Input.isKeyPressed(Window.WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT))
		{
			getCamera2d().getZoom().mul(0.9f);
		}


		renderer.getLight().setPosition(Mouse.getPosition(getCamera2d()));


		for(var o : list) o.run();
		list.sync();

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
		if(button == Mouse.Button.RIGHT)
			getCamera2d().focus(Mouse.getPosition(getCamera2d()));
	}
}
