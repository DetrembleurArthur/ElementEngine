import game.jgengine.debug.Logs;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.gui.widgets.Label;
import game.jgengine.graphics.gui.widgets.ProgressBar;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Scene2D;
import game.jgengine.sys.Window;
import game.jgengine.tweening.TweenAction;
import game.jgengine.tweening.TweenFunctions;
import game.jgengine.utils.Colors;
import org.joml.Vector2f;

public class MainScene extends Scene2D
{
	ProgressBar bar;
	Label label;
	int labelDragOrientation = 2;

	@Override
	public void load()
	{
		Window.WINDOW.setClearColor(Colors.RED);
	}

	float winXSize;
	@Override
	public void loadResources()
	{
		bar = new ProgressBar(00, 500, 200, new Vector2f(500, 75));
		bar.getShape().setPosition(new Vector2f(100, 100));
		//bar.enableMouseDragging();
		bar.enableHorizontalMouseDragging();
		bar.onValueChanged(sender ->
		{
			Window.WINDOW.setClearColor(Colors.interpolate(Colors.LIME, Colors.RED, bar.getShape().getPercent()));
			label.getShape().setText(bar.getValue() + " /" + bar.getShape().getMaxValue());
			label.getShape().setFillColor(bar.getShape().getProgressColor());
		});
		winXSize = Window.WINDOW.getSize().x;

		label = new Label(Registry.getFont("impact"), "none");
		label.getShape().setSizePx(50);
		label.enableVerticalMouseDragging();
		label.getShape().setPosition(new Vector2f(300, 300));

		label.onPositionChanged(sender -> bar.getShape().setBackgroundColor(Colors.random()));
		label.on(sender -> Logs.print("getFillColor changed"), "getFillColor");


		label.onMouseRightButtonReleased(sender -> label.changeDraggingOrientation((++labelDragOrientation)%3));
	}

	@Override
	public void update(double dt)
	{
		getCamera2d().activateKeys(Window.WINDOW, Camera2D.SPECTATOR_KEY_SET);
		bar.update();
		label.update();
		bar.getShape().setCurrentPercent(TweenAction.getProgress(TweenFunctions.LINEAR, 0, winXSize, Mouse.getPosition(getCamera2d()).x));
	}

	@Override
	public void render(double dt)
	{
		draw(bar.getShape());
		draw(label.getShape());
	}

	@Override
	public void close()
	{

	}

	@Override
	public void closeResources()
	{
		bar.getShape().destroy();
		label.getShape().destroy();

		Logs.print("Resources closed");
	}

	@Override
	public void keyPressedEventHandler(int key)
	{
	}
}
