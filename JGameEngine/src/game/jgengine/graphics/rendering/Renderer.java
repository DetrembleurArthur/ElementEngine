package game.jgengine.graphics.rendering;

import game.jgengine.entity.GameObject;
import game.jgengine.graphics.camera.Camera;
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


	public void render(GameObject gelem, Camera camera)
	{
		shader.start();
		shader.uploadMat4f("uModel", gelem.getTransformMatrix());
		shader.uploadMat4f("uView", camera.updateViewMatrix());
		shader.uploadMat4f("uProjection", camera.updateProjectionMatrix());
		if(gelem.getTexture() != null)
		{
			shader.setUniform1i("isTextured", 1);
			shader.uploadTexture("TEX_SAMPLER", 0);
		}
		else
		{
			shader.setUniform1i("isTextured", 0);
		}
		shader.setUniformf4("uFillColor", gelem.getFillColor());
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
