import game.jgengine.debug.Logs;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.loaders.TextureLoader;
import game.jgengine.graphics.shapes.Circle;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Scene2D;
import game.jgengine.sys.Window;
import game.jgengine.utils.Colors;
import game.jgengine.utils.Cursor;

import static game.jgengine.sys.Game.GAME;

public class MainScene extends Scene2D
{
	private Circle circle;

	@Override
	public void load()
	{
		Window.WINDOW.setClearColor(Colors.PURPLE);
		Window.WINDOW.setCursor(new Cursor("src/game/jgengine/sys/default-cursor.png"));
		Window.WINDOW.setDecorated(false);
	}

	@Override
	public void update(double dt)
	{

		getCamera2d().activateKeys(Window.WINDOW, Camera2D.SPECTATOR_KEY_SET);circle.setPosition(getMousePosition());
	}

	@Override
	public void render(double dt)
	{
		draw(circle);
	}

	@Override
	public void close()
	{
		Window.WINDOW.setCursor(new Cursor(Cursor.ARROW));
		Window.WINDOW.setDecorated(true);
	}

	@Override
	public void buttonPressedEventHandler(int button)
	{
		GAME.setCurrentScene("FPS");
	}

	@Override
	public void loadResources()
	{
		circle = new Circle(100, 20, Registry.getTexture("bricks"));
		circle.setCenterOrigin();
	}

	@Override
	public void closeResources()
	{
		circle.destroy();
	}
}
