package game.jgengine.sys;

import game.jgengine.debug.Logs;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.camera.OrthoProjectionSettings;
import org.joml.Vector2f;

public abstract class Scene2D extends Scene
{
	public Scene2D()
	{
		buildCamera();
		Logs.print(getClass().getName() + " created");
	}

	@Override
	final public void buildCamera()
	{
		camera = new Camera2D(new OrthoProjectionSettings(Window.WINDOW));
	}

	public Camera2D getCamera2d()
	{
		return (Camera2D)camera;
	}

	public Vector2f getMousePosition()
	{
		return Mouse.getPosition(getCamera2d());
	}
}
