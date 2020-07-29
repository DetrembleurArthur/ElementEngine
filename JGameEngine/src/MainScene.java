import game.jgengine.binding.NotifyProperty;
import game.jgengine.binding.Property;
import game.jgengine.debug.Logs;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.loaders.TextureLoader;
import game.jgengine.graphics.shapes.Circle;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Scene2D;
import game.jgengine.sys.Window;
import game.jgengine.utils.Colors;
import game.jgengine.utils.Cursor;
import org.joml.Vector2f;

import static game.jgengine.sys.Game.GAME;

public class MainScene extends Scene2D
{
	private Circle circle;
	private NotifyProperty<Vector2f> property1;
	private NotifyProperty<Vector2f> property2;

	@Override
	public void load()
	{
		Window.WINDOW.setClearColor(Colors.TURQUOISE);
	}

	@Override
	public void loadResources()
	{
		circle = new Circle(100, 20, Registry.getTexture("bricks"));
		circle.setCenterOrigin();
		circle.setPosition(Window.WINDOW.getCenter());

		//circle.getClass().getField("").get();

		property1 = new NotifyProperty<>(circle.getPosition2D());
		property2 = new NotifyProperty<>(property1.get());


		property1.bindBidirectional(property2);
	}

	@Override
	public void update(double dt)
	{

		getCamera2d().activateKeys(Window.WINDOW, Camera2D.SPECTATOR_KEY_SET);
		circle.setPosition(property2.get());
		property1.set(getMousePosition());
	}

	@Override
	public void render(double dt)
	{
		draw(circle);
	}

	@Override
	public void close()
	{

	}

	@Override
	public void closeResources()
	{
		circle.destroy();
	}
}
