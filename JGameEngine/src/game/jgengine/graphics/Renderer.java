package game.jgengine.graphics;

import game.jgengine.entity.GameObject;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.camera.Camera3D;
import game.jgengine.graphics.shaders.Shader;
import game.jgengine.sys.Window;

public class Renderer
{
	protected Shader shader;
	protected Window window;

	public Renderer(Shader shader, Window window)
	{
		this.shader = shader;
		this.window = window;
	}


	public void render(GraphicElement gelem, Camera3D camera3D)
	{
		shader.start();
		shader.uploadMat4f("uModel", gelem.getTransformMatrix());
		shader.uploadMat4f("uView", camera3D.updateViewMatrix());
		shader.uploadMat4f("uProjection", camera3D.getProjectionMatrix());
		gelem.draw();
		shader.stop();
	}

	public void render(GameObject gelem, Camera3D camera3D)
	{
		shader.start();
		shader.uploadMat4f("uModel", gelem.getTransformMatrix());
		shader.uploadMat4f("uView", camera3D.updateViewMatrix());
		shader.uploadMat4f("uProjection", camera3D.getProjectionMatrix());
		if(gelem.getTexture() != null)
			shader.uploadTexture("TEX_SAMPLER", 0);
		gelem.draw();
		shader.stop();
	}

	public void render(GraphicElement gelem, Camera2D camera2D)
	{
		shader.start();
		shader.uploadMat4f("uModel", gelem.getTransformMatrix());
		shader.uploadMat4f("uView", camera2D.updateViewMatrix());
		shader.uploadMat4f("uProjection", camera2D.getProjectionMatrix());
		gelem.draw();
		shader.stop();
	}

	public void render(GameObject gelem, Camera2D camera2D)
	{
		shader.start();
		shader.uploadMat4f("uModel", gelem.getTransformMatrix());
		shader.uploadMat4f("uView", camera2D.updateViewMatrix());
		shader.uploadMat4f("uProjection", camera2D.getProjectionMatrix());
		if(gelem.getTexture() != null)
		{
			shader.uploadTexture("TEX_SAMPLER", 0);
		}
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
