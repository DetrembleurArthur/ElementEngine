package game.jgengine.graphics.rendering;

import game.jgengine.entity.GameObject;
import game.jgengine.graphics.camera.Camera;
import game.jgengine.graphics.shaders.Shader;
import game.jgengine.sys.Window;

public class TargetRenderer extends Renderer
{
	private TargetTexture target;

	public TargetRenderer(Shader shader, Camera camera, TargetTexture texture)
	{
		super(shader, camera);
		target = texture;
	}

	@Override
	public void render(GameObject gelem)
	{
		target.bind();
		super.render(gelem);
		target.unbind();
	}

	public void render(GameObject[] gelem)
	{
		for(var o : gelem)
			super.render(o);
	}
}
