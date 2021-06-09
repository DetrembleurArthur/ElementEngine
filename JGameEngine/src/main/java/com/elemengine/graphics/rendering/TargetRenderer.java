package com.elemengine.graphics.rendering;

import com.elemengine.entity.GraphicElement;
import com.elemengine.graphics.camera.Camera;
import com.elemengine.graphics.shaders.Shader;

public class TargetRenderer extends Renderer
{
	private final TargetTexture target;

	public TargetRenderer(Shader shader, Camera camera, TargetTexture texture)
	{
		super(shader, camera);
		target = texture;
	}

	@Override
	public void render(GraphicElement gelem)
	{
		target.bind();
		super.render(gelem);
		target.unbind();
	}

	public void render(GraphicElement[] gelem)
	{
		for(var o : gelem)
			super.render(o);
	}
}
