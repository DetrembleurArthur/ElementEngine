package game.jgengine.graphics;

import game.jgengine.graphics.shaders.Shader;
import game.jgengine.graphics.shaders.Textured;
import game.jgengine.graphics.vertex.GraphicElement;
import game.jgengine.sys.Window;
import org.joml.Matrix4f;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class Renderer
{
	private Shader shader;
	private Window window;

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

	public void render(GraphicElement gelem, Camera camera, HashMap<String, Object> uniforms)
	{
		shader.start();
		shader.uploadMat4f("uModel", gelem.getTransformMatrix());
		shader.uploadMat4f("uView", camera.getViewMatrix());
		shader.uploadMat4f("uProjection", camera.getProjectionMatrix());
		for(String key : uniforms.keySet())
		{
			shader.setUniformf1(key, (float)uniforms.get(key));
		}
		gelem.draw();
		shader.stop();
	}

	public void render(Textured gelem, Camera camera)
	{
		shader.start();
		shader.uploadMat4f("uModel", gelem.getTransformMatrix());
		shader.uploadMat4f("uView", camera.getViewMatrix());
		shader.uploadMat4f("uProjection", camera.getProjectionMatrix());
		shader.updaloadTexture("TEX_SAMPLER", 0);
		gelem.getTexture().active();
		gelem.getTexture().bind();
		gelem.draw();
		gelem.getTexture().unbind();
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
