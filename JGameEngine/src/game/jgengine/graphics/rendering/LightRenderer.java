package game.jgengine.graphics.rendering;

import game.jgengine.debug.Logs;
import game.jgengine.entity.GameObject;
import game.jgengine.entity.GraphicElement;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.camera.Camera;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.shaders.Shader;
import game.jgengine.sys.Window;
import game.jgengine.time.Time;
import game.jgengine.utils.MathUtil;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static java.lang.Math.pow;
import static org.joml.Math.sqrt;

public class LightRenderer extends Renderer
{
	protected Light light;

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
		shader.setUniformf2("lightPosition", getTranformLightPosition());
		shader.setUniformf2("screenSize", Window.WINDOW.getAspectRatioSize());
		shader.setUniformf2("aspectPosition", Window.WINDOW.getAspectRationPosition());
		shader.setUniformf4("lightColor", light.getColor());
		shader.setUniformf4("basicPower", light.getBasicPower());
		shader.setUniformf1("lightRadius", light.getRadius());
		shader.setUniformf1("lightFade", light.getLightFade());
		gelem.draw();
		shader.stop();
	}

	private Vector2f getTranformLightPosition()
	{
		var pos = new Vector2f(light.getPosition());//new Vector2f(light.getPosition()).sub(((Camera2D)camera).getPosition());
		/*pos.y = Window.WINDOW.getAspectRatioSize().y - pos.y;
		pos.add(Window.WINDOW.getAspectRationPosition());*/

		/*pos.x += Window.WINDOW.getAspectRationPosition().x;
		pos.y += Window.WINDOW.getAspectRationPosition().y;*/
		//Logs.print(pos);
		return pos;
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
