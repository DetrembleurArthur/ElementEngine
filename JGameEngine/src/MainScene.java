import game.jgengine.binding.Converter;
import game.jgengine.debug.Logs;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.gui.widgets.*;
import game.jgengine.graphics.shapes.Line;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.graphics.shapes.Shape;
import game.jgengine.graphics.shapes.Triangle;
import game.jgengine.registry.Registry;
import game.jgengine.scripting.AndCondition;
import game.jgengine.scripting.OrCondition;
import game.jgengine.scripting.Script;
import game.jgengine.sys.Scene2D;
import game.jgengine.sys.Window;
import game.jgengine.tweening.TimedTweenAction;
import game.jgengine.tweening.TweenFunctions;
import game.jgengine.utils.Colors;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import java.awt.image.RGBImageFilter;
import java.sql.Date;
import java.time.Instant;

public class MainScene extends Scene2D
{
	Line line;

	@Override
	public void load()
	{

	}



	@Override
	public void loadResources()
	{
		line = new Line(new Vector2f(100, 100), new Vector2f(600, 100));
		line.setLineWeight(3);
		line.setFillColor(Colors.RED);


		Logs.print("Resources loaded");
	}

	@Override
	public void update(double dt)
	{
		getCamera2d().activateKeys(Window.WINDOW, Camera2D.SPECTATOR_KEY_SET);
		line.setEndPoint(Mouse.getPosition(getCamera2d()));
	}

	@Override
	public void render(double dt)
	{
		draw(line);
	}

	@Override
	public void close()
	{

	}

	@Override
	public void closeResources()
	{
		line.destroy();
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
}
