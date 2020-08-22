import game.jgengine.debug.Logs;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.gui.widgets.Label;
import game.jgengine.graphics.gui.widgets.WRectangle;
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
	Script script;

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
		rect.enableMouseDragging();

		rect.toR(1f, TweenFunctions.EASE_IN_OUT_CUBIC, 1000, TimedTweenAction.INFINITE_CYCLE, true);
		rect.toG(1f, TweenFunctions.EASE_IN_OUT_CUBIC, 1500, TimedTweenAction.INFINITE_CYCLE, true);
		rect.toB(1f, TweenFunctions.EASE_IN_OUT_CUBIC, 2000, TimedTweenAction.INFINITE_CYCLE, true);
		rect.stopAnimationSequence();
		rect.startAnimations();



		script = new Script();
		script.addAction(() -> rect.getShape().setPosition(Mouse.getPosition(getCamera2d())));
		script.setCondition(() -> rect.getShape().getR() < 0.5f);
		script.setCondition(new OrCondition()
				.addCondition(() -> rect.getShape().getR() < 0.5f)
				.addCondition(() -> rect.getShape().getG() < 0.5f)
				.addCondition(() -> rect.getShape().getB() < 0.5f));

		Logs.print("Resources loaded");
	}

	@Override
	public void update(double dt)
	{
		getCamera2d().activateKeys(Window.WINDOW, Camera2D.SPECTATOR_KEY_SET);

		rect.run();
		script.run();
	}

	@Override
	public void render(double dt)
	{
		draw(rect.getShape());
	}

	@Override
	public void close()
	{

	}

	@Override
	public void closeResources()
	{
		rect.getShape().destroy();
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
