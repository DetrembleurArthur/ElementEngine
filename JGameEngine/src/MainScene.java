import game.jgengine.binding.Property;
import game.jgengine.debug.Logs;
import game.jgengine.event.Input;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.gui.event.Event;
import game.jgengine.graphics.gui.event.MouseButtonDoubleClickEvent;
import game.jgengine.graphics.gui.event.MouseLeftButtonClickEvent;
import game.jgengine.graphics.gui.event.ValueChangedEvent;
import game.jgengine.graphics.gui.widgets.*;
import game.jgengine.graphics.rendering.TargetRenderer;
import game.jgengine.graphics.rendering.TargetTexture;
import game.jgengine.graphics.shaders.Shader;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.graphics.shapes.Slider;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Scene2D;
import game.jgengine.sys.Window;
import game.jgengine.tweening.TweenAction;
import game.jgengine.tweening.TweenFunctions;
import game.jgengine.utils.Colors;
import game.jgengine.utils.To;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

import javax.swing.*;
import java.sql.Date;
import java.time.Instant;

public class MainScene extends Scene2D
{

	WRectangle background;
	WRectangle rect;
	WSlider[] sliders;
	Label colorLabel;
	WProgressBar bar;

	Vector2f winSize;

	TargetRenderer target;
	TargetTexture targetTexture;
	WRectangle sceneView;

	Entry entry;

	@Override
	public void load()
	{
		Window.WINDOW.setClearColor(Colors.BLACK);
	}


	@Override
	public void loadResources()
	{
		winSize = Window.WINDOW.getSize();
		background = new WRectangle(Registry.getTexture("background"));
		background.getShape().setSize(Window.WINDOW.getSize());

		rect = new WRectangle(Registry.getTexture("folder"));
		rect.with(shape -> {
			shape.setFillColor(new Vector4f(Colors.WHITE));
			shape.setSize(Registry.getTexture("folder").getDimension().div(3));
			shape.setCenterOrigin();
			shape.setPosition(Window.WINDOW.getCenter());
		});

		rect.onMouseEntered(sender -> rect.getShape().setFillColor(rect.getShape().getFillColor().mul(1.3f)));
		rect.onMouseExited(sender -> rect.getShape().setFillColor(rect.getShape().getFillColor().div(1.3f)));
		rect.enableMouseDragging();


		sliders = new WSlider[3];
		sliders[0] = new WSlider(0f, 1f, 1f, new Vector2f(300, 10));
		sliders[0].with(shape -> {
			shape.setFillColor(Colors.RED);
			shape.setPosition(new Vector2f(30, 30));
		});
		sliders[0].onValueChanged(sender -> {
			float perc = sliders[0].getShape().getPercent();
			background.getShape().setR(perc);
		});

		sliders[1] = new WSlider(0f, 1f, 1f, new Vector2f(300, 10));
		sliders[1].with(shape -> {
			shape.setFillColor(Colors.GREEN);
			shape.setPosition(new Vector2f(30, 50));
		});
		sliders[1].onValueChanged(sender -> {
			float perc = sliders[1].getShape().getPercent();
			background.getShape().setG(perc);
		});

		sliders[2] = new WSlider(0f, 1f, 1f, new Vector2f(300, 10));
		sliders[2].with(shape -> {
			shape.setFillColor(Colors.BLUE);
			shape.setPosition(new Vector2f(30, 70));
		});
		sliders[2].onValueChanged(sender -> {
			float perc = sliders[2].getShape().getPercent();
			background.getShape().setB(perc);
		});




		colorLabel = new Label(Registry.getFont("impact"), "Background color: none");
		colorLabel.getShape().setSizePx(50);
		colorLabel.getShape().setPosition(new Vector2f(30, winSize.y - 100));

		bar = new WProgressBar(0, 3, 3, new Vector2f(300, 50));
		bar.enableHorizontalMouseDragging();
		bar.getShape().setPosition(new Vector2f(30, winSize.y - 200));

		background.onFillColorChanged(sender -> {
			var color = background.getShape().getFillColor();
			colorLabel.getShape().setFillColor(new Vector4f(1).sub(color));
			colorLabel.getShape().setA(1);
			colorLabel.getShape().setText("Background color: " + To.Vector4i(color.mul(255)));
			color.div(255);
			bar.getShape().setCurrentValue(color.x + color.y + color.z);
		});

		targetTexture = new TargetTexture(Window.WINDOW.getSize().mul(0.25f));
		target = new TargetRenderer(Shader.DEFAULT, getCamera2d(), targetTexture);
		sceneView = new WRectangle(targetTexture.getTexture());
		sceneView.enableMouseDragging();
		sceneView.getShape().setCenterOrigin();
		sceneView.getShape().getHomotetie().y = -1;
		sceneView.getShape().setTopLeftPosition(new Vector2f(0));



		entry = new Entry(Registry.getFont("impact"), 50);
		entry.getShape().setPosition(new Vector2f(600, 800));
		entry.enableMouseDragging();
	}

	@Override
	public void update(double dt)
	{
		getCamera2d().activateKeys(Window.WINDOW, Camera2D.SPECTATOR_KEY_SET);
		rect.update();
		sliders[0].update();
		sliders[1].update();
		sliders[2].update();
		background.update();
		colorLabel.update();
		bar.update();
		sceneView.update();
		entry.update();
	}

	@Override
	public void render(double dt)
	{
		draw(background.getShape());
		draw(rect.getShape());
		draw(sliders[0].getShape());
		draw(sliders[1].getShape());
		draw(sliders[2].getShape());
		draw(colorLabel.getShape());
		draw(bar.getShape());
		draw(entry.getShape());

		draw(target, background.getShape());
		draw(target, rect.getShape());
		draw(target, sliders[0].getShape());
		draw(target, sliders[1].getShape());
		draw(target, sliders[2].getShape());
		draw(target, colorLabel.getShape());
		draw(target, bar.getShape());
		draw(target, entry.getShape());

		draw(sceneView.getShape());
	}

	@Override
	public void close()
	{

	}

	@Override
	public void closeResources()
	{
		background.getShape().destroy();
		rect.getShape().destroy();
		sliders[0].getShape().destroy();
		sliders[1].getShape().destroy();
		sliders[2].getShape().destroy();
		colorLabel.getShape().destroy();
		bar.getShape().destroy();
		targetTexture.destroy();
		sceneView.getShape().destroy();
		entry.getShape().destroy();
		Logs.print("Resources closed");
	}

	@Override
	public void keyPressedEventHandler(int key)
	{
		entry.updateKeys(key);
		if(key == GLFW.GLFW_KEY_LEFT_CONTROL)
		{
			Logs.print("SCREENSHOT TOOK");
			Window.WINDOW
					.takeScreenShot(
					("demo_screenshots/" +
							Date.from(Instant.now()) + ".png")
					.replace(" ", "_")
					.replace(":", "-"));
		}
	}

	@Override
	public void keyRepeatedEventHandler(int key)
	{
		entry.updateKeys(key);
	}
}
