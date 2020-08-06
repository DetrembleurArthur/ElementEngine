import game.jgengine.debug.Logs;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.gui.widgets.Label;
import game.jgengine.graphics.shapes.ProgressBar;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Scene2D;
import game.jgengine.sys.Window;
import game.jgengine.utils.Colors;
import org.joml.Vector2f;

public class MainScene extends Scene2D
{
	ProgressBar bar;

	@Override
	public void load()
	{

	}

	@Override
	public void loadResources()
	{
		bar = new ProgressBar(0, 100, 100, new Vector2f(Window.WINDOW.getSize().x, 75));
		bar.setFillColor(Colors.LIME);
	}

	@Override
	public void update(double dt)
	{
		getCamera2d().activateKeys(Window.WINDOW, Camera2D.SPECTATOR_KEY_SET);
		bar.setPosition(Mouse.getPosition(getCamera2d()));
		bar.setCurrentValue(bar.getCurrentValue() - 0.5f);
	}

	@Override
	public void render(double dt)
	{
		draw(bar);
	}

	@Override
	public void close()
	{

	}

	@Override
	public void closeResources()
	{
		bar.destroy();

		Logs.print("Resources closed");
	}

	@Override
	public void keyPressedEventHandler(int key)
	{
	}
}
