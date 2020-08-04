import game.jgengine.debug.Logs;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.gui.Label;
import game.jgengine.graphics.gui.event.Event;
import game.jgengine.graphics.gui.event.MouseExitedEvent;
import game.jgengine.graphics.gui.event.MouseLeftButtonClickEvent;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Scene2D;
import game.jgengine.sys.Window;
import game.jgengine.utils.Colors;

public class MainScene extends Scene2D
{
	Label label;

	@Override
	public void load()
	{

	}

	@Override
	public void loadResources()
	{
		label = new Label(Registry.getFont("impact"), "Hello world!");
		label.getShape().setSizePx(100);
		label.getShape().setPosition(Window.WINDOW.getCenter());
		label.getShape().setCenterOrigin();
		label.getShape().setFillColor(Colors.BLACK);

		label.onMouseExited(sender -> label.getShape().setFillColor(Colors.BLACK));
		label.onMouseLeftButtonClicked(sender -> label.getShape().setFillColor(Colors.RED), true);
		label.onMouseHovering(sender -> label.getShape().setFillColor(Colors.BLUE));
		label.setEventPriority(MouseLeftButtonClickEvent.class, Event.HIGHER_PRIORITY);
	}

	@Override
	public void update(double dt)
	{
		getCamera2d().activateKeys(Window.WINDOW, Camera2D.SPECTATOR_KEY_SET);
		label.update();
	}

	@Override
	public void render(double dt)
	{
		draw(label.getShape());
	}

	@Override
	public void close()
	{

	}

	@Override
	public void closeResources()
	{
		label.getShape().destroy();

		Logs.print("Resources closed");
	}

	@Override
	public void keyPressedEventHandler(int key)
	{
	}
}
