package com.elemengine.sys;

import com.elemengine.debug.Log;
import com.elemengine.event.Mouse;
import com.elemengine.graphics.camera.Camera2D;
import com.elemengine.graphics.camera.OrthoProjectionSettings;
import com.elemengine.graphics.shapes.Rectangle;
import com.elemengine.registry.Registry;
import org.joml.Vector2f;

public abstract class Scene2D extends Scene
{
	public Scene2D()
	{
		super();
		Log.print(getClass().getName() + " created");
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
