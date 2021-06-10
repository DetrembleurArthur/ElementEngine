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

	public void activeArrow()
	{
		Rectangle arrowLeft = new Rectangle(Registry.getTexture("arrow.png"));
		arrowLeft.setCenterOrigin();
		arrowLeft.setRotation(180);
		arrowLeft.getSize().mul(0.1f, 0.1f, 1f);
		arrowLeft.setCenterOrigin();
		arrowLeft.setTopLeftPosition(new Vector2f(10, 10));
		arrowLeft.events_c().onMouseLeftButtonReleased(sender -> getSignal().previous = true);

		Rectangle arrowRight = new Rectangle(Registry.getTexture("arrow.png"));
		arrowRight.getSize().mul(0.1f, 0.1f, 1f);
		arrowRight.setTopRightPosition(new Vector2f(Window.WINDOW.getSize().x - 10, 10));
		arrowRight.events_c().onMouseLeftButtonReleased(sender -> getSignal().next = true);

		getLayoutMap().create("gui", 1).put("gui", arrowLeft, arrowRight);
	}

	public Vector2f getMousePosition()
	{
		return Mouse.getPosition(getCamera2d());
	}
}
