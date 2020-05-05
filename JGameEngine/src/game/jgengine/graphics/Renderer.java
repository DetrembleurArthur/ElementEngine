package game.jgengine.graphics;

import game.jgengine.graphics.shaders.Shader;
import game.jgengine.sys.Window;

import java.util.HashMap;

public class Renderer
{
	protected Shader shader;
	protected Window window;

	public Renderer(Shader shader, Window window)
	{
		this.shader = shader;
		this.window = window;
	}


	public void render(GraphicElement gelem, Camera camera)
	{
		shader.start();
		shader.uploadMat4f("uModel", gelem.getTransformMatrix());
		shader.uploadMat4f("uView", camera.getViewMatrix());
		shader.uploadMat4f("uProjection", camera.getProjectionMatrix());
		gelem.draw();
		shader.stop();
	}

	public void render(GameObject gelem, Camera camera)
	{
		shader.start();
		shader.uploadMat4f("uModel", gelem.getTransformMatrix());
		shader.uploadMat4f("uView", camera.getViewMatrix());
		shader.uploadMat4f("uProjection", camera.getProjectionMatrix());
		if(gelem.getTexture() != null)
			shader.uploadTexture("TEX_SAMPLER", 0);
		gelem.draw();
		shader.stop();
	}


	public Shader getShader()
	{
		return shader;
	}

	public void setShader(Shader shader)
	{
		this.shader = shader;
	}

	public Window getWindow()
	{
		return window;
	}

	public void setWindow(Window window)
	{
		this.window = window;
	}
}
