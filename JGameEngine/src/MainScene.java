import game.jgengine.binding.Property;
import game.jgengine.debug.Logs;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.loaders.TextureLoader;
import game.jgengine.graphics.rendering.Texture;
import game.jgengine.graphics.shapes.Circle;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Game;
import game.jgengine.sys.Scene2D;
import game.jgengine.sys.Window;
import game.jgengine.utils.Colors;
import game.jgengine.utils.Cursor;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

import static game.jgengine.sys.Game.GAME;

public class MainScene extends Scene2D
{
	public static class MyCircle extends Circle
	{
		public Property<Vector4f> FillColor = new Property<Vector4f>()
		{
			@Override
			protected void setValue(Vector4f value)
			{
				setFillColor(value);
			}

			@Override
			public Vector4f getValue()
			{
				return getFillColor();
			}
		};

		public MyCircle(float radius, int points, Texture texture)
		{
			super(radius, points, texture);
		}
	}

	Property<Vector4f> BackgroundColor = new Property<Vector4f>()
	{
		@Override
		protected void setValue(Vector4f value)
		{
			Window.WINDOW.setClearColor(value);
		}

		@Override
		public Vector4f getValue()
		{
			return Window.WINDOW.getClearColor();
		}
	};

	private MyCircle circle;
	@Override
	public void load()
	{
		BackgroundColor.set(Colors.TURQUOISE);
	}

	@Override
	public void loadResources()
	{
		circle = new MyCircle(100, 20, Registry.getTexture("bricks"));
		circle.setCenterOrigin();
		circle.setPosition(Window.WINDOW.getCenter());


		circle.FillColor.bindBidirectional(BackgroundColor,
				(Vector4f value) -> new Vector4f(1f-value.x, 1f-value.y, 1f-value.z, 1f),
				(Vector4f value) -> new Vector4f(1f-value.x, 1f-value.y, 1f-value.z, 1f));

		Logs.print("Resources loaded: " + circle.FillColor.getValue());
	}

	@Override
	public void update(double dt)
	{

		getCamera2d().activateKeys(Window.WINDOW, Camera2D.SPECTATOR_KEY_SET);
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

	@Override
	public void keyPressedEventHandler(int key)
	{
		if(key == GLFW.GLFW_KEY_SPACE)
			BackgroundColor.set(Colors.random());
	}
}
