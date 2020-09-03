import game.jgengine.debug.Logs;
import game.jgengine.entity.Dynamic;
import game.jgengine.entity.GraphicElement;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.gui.widgets.Label;
import game.jgengine.graphics.gui.widgets.WProgressBar;
import game.jgengine.graphics.gui.widgets.WRectangle;
import game.jgengine.graphics.shapes.Circle;
import game.jgengine.graphics.shapes.ProgressBar;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.graphics.shapes.Triangle;
import game.jgengine.graphics.texts.Text;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Scene2D;
import game.jgengine.sys.Window;
import game.jgengine.tweening.TimedTweenAction;
import game.jgengine.tweening.TweenFunctions;
import game.jgengine.utils.Colors;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;

public class MainScene extends Scene2D
{

	ArrayList<Dynamic> list = new ArrayList<>();

	Label rect;

	@Override
	public void load()
	{
		rect = new Label(Registry.getFont("impact"), "Hello world!");
		rect.with(shape -> {
			shape.setTextWidth(500);
			shape.setPosition(Window.WINDOW.getCenter());
			shape.setCenterOrigin();
			shape.setFillColor(Colors.RED);
		});
		rect.enableMouseDragging();
		rect.onMouseEntered(sender -> {
			rect.flushAnimations();
			rect.toColor(Colors.LIME, TweenFunctions.EASE_IN_OUT_QUAD, 300);
			rect.stopAnimationSequenceConfiguration();
			rect.startAnimations();
		});

		rect.onMouseExited(sender -> {
			rect.flushAnimations();
			rect.toColor(Colors.RED, TweenFunctions.EASE_IN_OUT_QUAD, 500);
			rect.stopAnimationSequenceConfiguration();
			rect.startAnimations();
		});

		list.add(rect);
	}


	@Override
	public void loadResources()
	{

		Logs.print("Resources loaded");
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
}
