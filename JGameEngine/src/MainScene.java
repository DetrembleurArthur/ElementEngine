import game.jgengine.binding.Converter;
import game.jgengine.debug.Logs;
import game.jgengine.entity.Dynamic;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.gui.widgets.Entry;
import game.jgengine.graphics.gui.widgets.Label;
import game.jgengine.graphics.gui.widgets.SmartRectangle;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Game;
import game.jgengine.sys.Scene2D;
import game.jgengine.sys.Window;
import game.jgengine.time.DynamicTimer;
import game.jgengine.tweening.TimedTweenAction;
import game.jgengine.tweening.TweenFunctions;
import game.jgengine.utils.Colors;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;

public class MainScene extends Scene2D
{

	ArrayList<Dynamic> list = new ArrayList<>();

	Label title;
	Label start;
	Label option;
	Label quit;

	@Override
	public void load()
	{

	}


	@Override
	public void loadResources()
	{
		var font = Registry.getFont("impact");
		title = new Label(font, "Game title");
		start = new Label(font, "start");
		option = new Label(font, "options");
		quit = new Label(font, "quit");

		Converter<Vector4f, Vector4f> converter = value -> new Vector4f(1f-value.x, 1f-value.y, 1f-value.z, 1f);
		start.fillColorProperty.bind(title.fillColorProperty, converter);
		option.fillColorProperty.bind(title.fillColorProperty, converter);
		quit.fillColorProperty.bind(title.fillColorProperty, converter);

		title.with(shape -> {
			shape.setCenterOrigin();
			shape.setTextWidth(1000);
			shape.setPosition(Window.WINDOW.getCenter().mul(1, 0.50f));
		});
		title.newAnimation("floating")
		.toPosition(Window.WINDOW.getCenter().mul(1, 0.43f), TweenFunctions.EASE_IN_OUT_QUAD, 1500, TimedTweenAction.INFINITE_CYCLE, true);
		title.startAnimation("floating");


		start.with(shape -> {
			shape.setCenterOrigin();
			shape.setTextHeight(50);
			shape.setPosition(Window.WINDOW.getCenter());
		});
		start.newAnimation("enter")
		.toSize(start.sizeProperty.getValue().mul(1.5f), TweenFunctions.EASE_IN_OUT_QUAD, 150, 1, false)
		.toColor(Colors.LIME, TweenFunctions.EASE_IN_OUT_QUAD, 500, TimedTweenAction.INFINITE_CYCLE, true);
		start.newAnimation("exit")
		.fromToSize(start.sizeProperty.getValue().mul(1.5f), start.sizeProperty.getValue(), TweenFunctions.EASE_IN_OUT_QUAD, 150, 1, false)
		.fromToColor(Colors.LIME, Colors.WHITE, TweenFunctions.EASE_IN_OUT_QUAD, 500, 1, false);

		start.onMouseEntered(sender -> {
			start.stopAnimation("exit");
			start.startAnimation("enter");
		});

		start.onMouseExited(sender -> {
			start.stopAnimation("enter");
			start.startAnimation("exit");
		});



		option.with(shape -> {
			shape.setCenterOrigin();
			shape.setTextHeight(50);
			shape.setPosition(Window.WINDOW.getCenter().mul(1, 1.3f));
		});

		option.newAnimation("enter")
				.toSize(option.sizeProperty.getValue().mul(1.5f), TweenFunctions.EASE_IN_OUT_QUAD, 150, 1, false)
				.toColor(Colors.BLUE, TweenFunctions.EASE_IN_OUT_QUINT, 500, TimedTweenAction.INFINITE_CYCLE, true);
		option.newAnimation("exit")
				.fromToSize(option.sizeProperty.getValue().mul(1.5f), option.sizeProperty.getValue(), TweenFunctions.EASE_IN_OUT_QUAD, 150, 1, false)
				.fromToColor(Colors.BLUE, Colors.WHITE, TweenFunctions.EASE_IN_OUT_QUINT, 500, 1, false);




		option.onMouseEntered(sender -> {
			option.stopAnimation("exit");
			option.startAnimation("enter");
		});

		option.onMouseExited(sender -> {
			option.stopAnimation("enter");
			option.startAnimation("exit");
		});


		quit.with(shape -> {
			shape.setCenterOrigin();
			shape.setTextHeight(50);
			shape.setPosition(Window.WINDOW.getCenter().mul(1, 1.6f));
		});

		quit.newAnimation("enter")
				.toSize(quit.sizeProperty.getValue().mul(1.5f), TweenFunctions.EASE_IN_OUT_QUAD, 150, 1, false)
				.toColor(Colors.RED, TweenFunctions.EASE_IN_OUT_CIRC, 500, TimedTweenAction.INFINITE_CYCLE, true);
		quit.newAnimation("exit")
				.fromToSize(quit.sizeProperty.getValue().mul(1.5f), quit.sizeProperty.getValue(), TweenFunctions.EASE_IN_OUT_QUAD, 150, 1, false)
				.fromToColor(Colors.RED, Colors.WHITE, TweenFunctions.EASE_IN_OUT_CIRC, 500, 1, false);



		quit.onMouseEntered(sender -> {
			quit.stopAnimation("exit");
			quit.startAnimation("enter");
		});

		quit.onMouseExited(sender -> {
			quit.stopAnimation("enter");
			quit.startAnimation("exit");
		});

		quit.onMouseLeftButtonReleased(sender -> Window.WINDOW.close());

		list.add(title);
		list.add(start);
		list.add(option);
		list.add(quit);
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
