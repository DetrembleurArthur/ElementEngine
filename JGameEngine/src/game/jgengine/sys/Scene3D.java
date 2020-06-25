package game.jgengine.sys;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.camera.Camera3D;
import game.jgengine.graphics.camera.PerspProjectionSettings;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

public abstract class Scene3D extends Scene
{
	protected boolean centered = false;

	public Scene3D()
	{
		buildCamera();
	}

	@Override
	protected void buildCamera()
	{
		camera = new Camera3D(new PerspProjectionSettings(70f, Window.WINDOW));
	}

	public Camera3D getCamera3D()
	{
		return (Camera3D)camera;
	}

	@Override
	public void keyPressedEventHandler(int key)
	{
		if(key == GLFW.GLFW_KEY_ESCAPE)
		{
			centered = !centered;
			if(centered)
			{
				getCamera3D().setOldMouse(Mouse.getPosition());
				Window.WINDOW.disableCursor();
			}
			else
			{
				Window.WINDOW.resetCursor();
			}
		}
	}

	@Override
	public void cursorMovedEventHandler(double xpos, double ypos)
	{
		if(centered)
			getCamera3D().update(new Vector2f((float)xpos, (float)ypos));
	}
}
