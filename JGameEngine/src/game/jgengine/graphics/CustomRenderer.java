package game.jgengine.graphics;

import game.jgengine.entity.GameObject;
import game.jgengine.graphics.camera.Camera3D;
import game.jgengine.graphics.shaders.Shader;
import game.jgengine.sys.Window;

public class CustomRenderer extends Renderer
{
	public static interface UniformSetter
	{
		public void set(Shader shader, GameObject gelem);
	}

	private UniformSetter setter = null;

	public CustomRenderer(Shader shader, Window window, UniformSetter setter)
	{
		super(shader, window);
		this.setter = setter;
	}

	@Override
	public void render(GameObject gelem, Camera3D camera3D)
	{
		shader.start();
		shader.uploadMat4f("uModel", gelem.getTransformMatrix());
		shader.uploadMat4f("uView", camera3D.getViewMatrix());
		shader.uploadMat4f("uProjection", camera3D.getProjectionMatrix());
		if(gelem.getTexture() != null)
			shader.uploadTexture("TEX_SAMPLER", 0);
		if(gelem.getFillColor() != null)
			shader.uploadf4("fColor", gelem.getFillColor());
		setter.set(shader, gelem);
		gelem.draw();
		shader.stop();
	}

}
