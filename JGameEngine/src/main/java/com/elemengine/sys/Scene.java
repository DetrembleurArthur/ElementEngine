package com.elemengine.sys;

import com.elemengine.debug.Log;
import com.elemengine.entity.Dynamic;
import com.elemengine.event.handler.EventCollector;
import com.elemengine.event.handler.EventHandler;
import com.elemengine.event.handler.annotations.OnEvent;
import com.elemengine.graphics.camera.Camera;
import com.elemengine.graphics.rendering.Renderer;
import com.elemengine.graphics.shaders.Shader;

public abstract class Scene implements Manageable, ResourcesManageable
{
	protected final EventCollector eventCollector;
	protected Camera camera;
	protected Renderer defaultRenderer;

	public Scene()
	{
		eventCollector = new EventCollector(this);
		buildCamera();
		defaultRenderer = new Renderer(Shader.DEFAULT, camera);
	}

	protected abstract void buildCamera();

	public Camera getCamera()
	{
		return camera;
	}

	public Renderer getDefaultRenderer()
	{
		return defaultRenderer;
	}

	public void draw(Dynamic gobject)
	{
		gobject.draw(defaultRenderer);
	}

	public void draw(Renderer renderer, Dynamic gobject)
	{
		gobject.draw(renderer);
	}

	@OnEvent(OnEvent.Types.WINDOW_RESIZED)
	final public void windowResizedEventHandler(int width, int height)
	{
		Window.WINDOW.aspectRatioUpdateViewport(width, height);
	}

	public EventCollector getEventCollector()
	{
		return eventCollector;
	}
}
