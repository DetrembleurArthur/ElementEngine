import game.jgengine.graphics.texts.Text;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Scene2D;
import game.jgengine.sys.Window;
import game.jgengine.tweening.TimedTweenAction;
import game.jgengine.tweening.TweenFunctions;
import game.jgengine.utils.Colors;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static game.jgengine.sys.Game.GAME;

public class MenuScene extends Scene2D
{
	private Text text;
	private TimedTweenAction action;

	@Override
	public void load()
	{
		Window.WINDOW.setClearColor(Colors.BLACK);
		action.start();
	}

	@Override
	public void update(double dt)
	{
		action.run();
	}

	@Override
	public void render(double dt)
	{
		draw(text);
	}

	@Override
	public void close()
	{
		action.stop();
	}

	@Override
	public void buttonPressedEventHandler(int button)
	{
		GAME.setCurrentScene("MAIN");
	}

	@Override
	public void loadResources()
	{
		text = new Text(Registry.getFont("impact"), "Game Title!");
		text.setSizePx(150);
		text.setFillColor(Colors.WHITE);
		text.setPosition(Window.WINDOW.getCenter().mul(1, 0.8f));
		text.setCenterOrigin();

		action = new TimedTweenAction(
				text.getPosition2D().y,
				text.getPosition2D().y - 30,
				TweenFunctions.EASE_IN_OUT_QUAD,
				(v) -> {text.setPosition(new Vector2f(text.getPosition2D().x, v));},
				2000,
				TimedTweenAction.INFINITE_CYCLE,
				true
		);
	}

	@Override
	public void closeResources()
	{
		text.destroy();
	}
}
