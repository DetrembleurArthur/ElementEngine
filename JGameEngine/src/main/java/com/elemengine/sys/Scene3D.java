package com.elemengine.sys;

import com.elemengine.event.Mouse;
import com.elemengine.event.handler.annotations.OnEvent;
import com.elemengine.graphics.camera.Camera3D;
import com.elemengine.graphics.camera.PerspProjectionSettings;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

public abstract class Scene3D extends Scene
{
	protected boolean centered = false;

	public Scene3D()
	{
		super();
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

	@OnEvent(OnEvent.Types.KEY_PRESSED)
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

	@OnEvent(OnEvent.Types.CURSOR_MOVED)
	public void cursorMovedEventHandler(double xpos, double ypos)
	{
		if(centered)
			getCamera3D().update(new Vector2f((float)xpos, (float)ypos));
	}
}
