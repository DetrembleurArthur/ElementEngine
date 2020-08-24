import game.jgengine.binding.Converter;
import game.jgengine.debug.Logs;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.gui.widgets.*;
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
import java.sql.Date;
import java.time.Instant;

public class MainScene extends Scene2D
{
	WRectangle rect;
	WCircle circle;


	WSlider slider;
	WProgressBar bar;

	@Override
	public void load()
	{

	}



	@Override
	public void loadResources()
	{
		rect = new WRectangle(Registry.getTexture("bricks"));
		rect.with(shape -> {
			shape.setFillColor(Colors.BLACK);
			shape.setSize(300, 300);
			shape.setCenterOrigin();
			shape.setPosition(Window.WINDOW.getCenter());
		});
		//rect.enableMouseDragging();

		circle = new WCircle(50, 30, null);
		circle.getShape().setCenterOrigin();
		circle.getShape().setFillColor(Colors.BLACK);
		circle.getShape().setPosition(Window.WINDOW.getCenter());

		rect.enableMouseDragging();

		rect.xProperty.bind(circle.yProperty);
		rect.yProperty.bind(circle.xProperty);
		rect.redProperty.bind(circle.redProperty, value -> 1f-value);

		rect.toR(1, TweenFunctions.EASE_IN_OUT_QUART, 3000, TimedTweenAction.INFINITE_CYCLE, true);
		rect.stopAnimationSequence();
		rect.startAnimations();

		circle.addScript(new Script().setCondition(() -> circle.redProperty.getValue() < 0.5f).addAction(() -> Logs.print("Action 1")));


		slider = new WSlider(0, 300, 0, new Vector2f(300, 50));

		slider.positionProperty.set(Window.WINDOW.getCenter().add(0, 300));
		slider.valueProperty.set(0f);
		bar = new WProgressBar(0, 300, 0, new Vector2f(300, 50));

		bar.enableHorizontalMouseDragging();
		slider.valueProperty.bindBidirectional(bar.valueProperty);

		float winx = Window.WINDOW.getSize().x;
		bar.xProperty.bindBidirectional(bar.valueProperty, value -> (value/winx)*bar.getShape().getMaxValue());


		Logs.print("Resources loaded");
	}

	@Override
	public void update(double dt)
	{
		getCamera2d().activateKeys(Window.WINDOW, Camera2D.SPECTATOR_KEY_SET);

		rect.run();
		circle.run();
		slider.run();
		bar.run();
	}

	@Override
	public void render(double dt)
	{
		draw(rect.getShape());
		draw(circle.getShape());
		draw(slider.getShape());
		draw(bar.getShape());
	}

	@Override
	public void close()
	{

	}

	@Override
	public void closeResources()
	{
		rect.getShape().destroy();
		circle.getShape().destroy();
		slider.getShape().destroy();
		bar.getShape().destroy();
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
	}
}
