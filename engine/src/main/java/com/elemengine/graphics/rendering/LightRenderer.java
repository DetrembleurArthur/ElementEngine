package com.elemengine.graphics.rendering;

import com.elemengine.entity.GameObject;
import com.elemengine.entity.GraphicElement;
import com.elemengine.graphics.camera.Camera;
import com.elemengine.graphics.camera.Camera2D;
import com.elemengine.graphics.shaders.Shader;
import com.elemengine.sys.Window;

import static org.joml.Math.sqrt;

public class LightRenderer extends Renderer
{
	private Light light;

	public LightRenderer(Camera camera, Light light)
	{
		super(Shader.LIGHT, camera);
		this.light = light;
	}

	public Light getLight()
	{
		return light;
	}

	public void setLight(Light light)
	{
		this.light = light;
	}

	@Override
	public void render(GraphicElement gelem)
	{
		shader.start();
		shader.uploadMat4f("uModel", gelem.getTransformMatrix());
		shader.uploadMat4f("uView", camera.updateViewMatrix());
		shader.uploadMat4f("uProjection", camera.updateProjectionMatrix());
		if(gelem instanceof GameObject && gelem.getTexture() != null)
		{
			shader.setUniform1i("isTextured", 1);
			shader.uploadTexture("TEX_SAMPLER", 0);
		}
		else
		{
			shader.setUniform1i("isTextured", 0);
		}

		shader.setUniformf4("uFillColor", gelem.getFillColor());
		shader.setUniformf2("screenSize", Window.WINDOW.getAspectRatioSize());
		shader.setUniformf2("aspectPosition", Window.WINDOW.getAspectRatioPosition());

		shader.setUniformf2("lightPosition", light.getPosition());
		shader.setUniformf4("lightColor", light.getColor());
		shader.setUniformf4("basicPower", light.getBasicPower());
		shader.setUniformf1("lightRadius", light.getRadius() * Window.WINDOW.trueRatio * ((Camera2D)camera).getZoom().x);
		shader.setUniformf1("lightFade", light.getLightFade());
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

	public Camera getCamera()
	{
		return camera;
	}

	public void setCamera(Camera camera)
	{
		this.camera = camera;
	}
}
