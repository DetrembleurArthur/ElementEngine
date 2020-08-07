package game.jgengine.sys;

import game.jgengine.entity.GameObject;
import game.jgengine.event.handler.EventHandler;
import game.jgengine.graphics.camera.Camera;
import game.jgengine.graphics.rendering.Renderer;
import game.jgengine.graphics.shaders.Shader;

public abstract class Scene implements Manageable, ResourcesManageable, EventHandler
{
	protected Camera camera;
	protected Renderer defaultRenderer;

	public Scene()
	{
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

	public void draw(GameObject gobject)
	{
		gobject.draw(defaultRenderer);
	}

	@Override
	final public void windowResizedEventHandler(int width, int height)
	{
		Window.WINDOW.aspectRatioUpdateViewport(width, height);
	}


}
