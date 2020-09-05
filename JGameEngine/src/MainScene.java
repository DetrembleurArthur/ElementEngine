import game.jgengine.entity.Dynamic;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.shapes.ProgressBar;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.registry.Registry;
import game.jgengine.scripting.Script;
import game.jgengine.sys.Scene2D;
import game.jgengine.sys.Window;
import game.jgengine.tweening.TimedTweenAction;
import game.jgengine.tweening.TFunc;
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
	ProgressBar bar;
	ProgressBar barProgress;

	Rectangle background;


	@Override
	public void load()
	{

	}


	@Override
	public void loadResources()
	{
		float swidth = Window.WINDOW.getSize().x;

		background = new Rectangle(Registry.getTexture("background"));
		background.setSize(Window.WINDOW.getSize());

		rect = new Rectangle(Registry.getTexture("bricks"));
		rect.setCenterOrigin();
		rect.setSize(150, 150);

		rect.properties();
		rect.events().enableVerticalMouseDragging();

		rect.animations().create("left-right")
				.toX(0, swidth, TFunc.EASE_IN_OUT_ELASTIC, 5000, TimedTweenAction.INFINITE_CYCLE, true)
				.toRotation(0, 360, TFunc.LINEAR, 5000, TimedTweenAction.INFINITE_CYCLE, true);


		bar = new ProgressBar(0, 100, 50, new Vector2f(500, 100));
		bar.setMinColor(Colors.MAGENTA);
		bar.setMaxColor(Colors.TURQUOISE);
		bar.setCenterPosition(Window.WINDOW.getCenter());
		bar.events().enableHorizontalMouseDragging();

		barProgress = new ProgressBar(0, 100, 50, new Vector2f(500, 100));
		barProgress.setMinColor(Colors.MAGENTA);
		barProgress.setMaxColor(Colors.TURQUOISE);
		barProgress.setCenterPosition(Window.WINDOW.getCenter().sub(0, -150));
		barProgress.events().enableHorizontalMouseDragging();

		rect.properties().xProperty.bind(bar.valueProperty().valueProperty, value -> (value / swidth) *  bar.getMaxValue());
		rect.properties().xProperty.bind(barProgress.valueProperty().valueProperty, value -> {
			return rect.animations().getAnimation("left-right").getActions().get(0).getCurrentPercent() * barProgress.getMaxValue();
		});

		rect.animations().startAnimation("left-right");

		list.add(background);
		list.add(rect);
		list.add(bar);
		list.add(barProgress);

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
	public void textInputEventHandler(int codepoint)
	{

	}
}
