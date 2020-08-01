import game.jgengine.binding.Converter;
import game.jgengine.binding.Property;
import game.jgengine.debug.Logs;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.loaders.TextureLoader;
import game.jgengine.graphics.rendering.Texture;
import game.jgengine.graphics.shapes.Circle;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.graphics.texts.Text;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Game;
import game.jgengine.sys.Scene2D;
import game.jgengine.sys.Window;
import game.jgengine.tweening.TimedTweenAction;
import game.jgengine.tweening.TweenFunctions;
import game.jgengine.utils.Colors;
import game.jgengine.utils.Cursor;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

import static game.jgengine.sys.Game.GAME;

public class MainScene extends Scene2D
{
	Text text;
	Rectangle rect;

	Property<String> TextProperty;
	Property<Float> XProperty;

	TimedTweenAction tween;

	@Override
	public void load()
	{
		tween.start();
	}

	@Override
	public void loadResources()
	{
		text = new Text(Registry.getFont("impact"), "0%");
		text.setSizePx(50);
		text.setPosition(new Vector2f(20, 20));
		text.setFillColor(Colors.BLUE);

		rect = new Rectangle(null);
		rect.setFillColor(Colors.RED);
		rect.setSize(100, 100);
		rect.setCenterOrigin();
		rect.setPosition(new Vector2f(0, Window.WINDOW.getSize().y / 2f));

		TextProperty = new Property<String>()
		{
			@Override
			public void setValue(String value)
			{
				text.setText(value);
			}

			@Override
			public String getValue()
			{
				return text.getText();
			}
		};

		XProperty = new Property<Float>()
		{
			@Override
			public void setValue(Float value)
			{
				rect.setPosition(new Vector2f(value, rect.getPosition().y));
			}

			@Override
			public Float getValue()
			{
				return tween.getCurrentPercent() * 100f;
			}
		};

		XProperty.bind(TextProperty, value -> value.intValue() + "%");

		tween = new TimedTweenAction(
				0,
				Window.WINDOW.getSize().x,
				TweenFunctions.EASE_IN_OUT_QUAD,
				value -> XProperty.set(value),
				10000f,
				TimedTweenAction.INFINITE_CYCLE,
				true);


	}

	@Override
	public void update(double dt)
	{
		//getCamera2d().activateKeys(Window.WINDOW, Camera2D.SPECTATOR_KEY_SET);
		tween.run();
	}

	@Override
	public void render(double dt)
	{
		draw(text);
		draw(rect);
	}

	@Override
	public void close()
	{

	}

	@Override
	public void closeResources()
	{
		text.destroy();
		rect.destroy();
	}

	@Override
	public void keyPressedEventHandler(int key)
	{
	}
}
