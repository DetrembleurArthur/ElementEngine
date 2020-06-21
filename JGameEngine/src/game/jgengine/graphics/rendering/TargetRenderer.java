package game.jgengine.graphics.rendering;

import game.jgengine.entity.GameObject;
import game.jgengine.graphics.camera.Camera;
import game.jgengine.graphics.shaders.Shader;
import game.jgengine.sys.Window;

public class TargetRenderer extends Renderer
{
	private TargetTexture target;

	public TargetRenderer(Shader shader, Window window, TargetTexture texture)
	{
		super(shader, window);
		target = texture;
	}

	@Override
	public void render(GameObject gelem, Camera camera)
	{
		target.bind();
		super.render(gelem, camera);
		target.unbind();
	}

	public void render(GameObject[] gelem, Camera camera)
	{
		target.bind();
		for(var o : gelem)
			render(o, camera);
		target.unbind();
	}
}
