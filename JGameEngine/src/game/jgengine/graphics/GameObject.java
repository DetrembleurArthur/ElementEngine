package game.jgengine.graphics;

import game.jgengine.graphics.shaders.Texture;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.system.CallbackI;

public class GameObject extends GraphicElement
{
	private Texture texture = null;
	private Vector4f fillColor = null;

	protected GameObject(Mesh mesh, Texture texture)
	{
		super(mesh);
		setTexture(texture);
	}

	public Texture getTexture()
	{
		return texture;
	}

	public void setTexture(Texture texture)
	{
		this.texture = texture;
	}

	public void setFillColor(Vector4f color)
	{
		this.fillColor = color;
	}

	public Vector4f getFillColor()
	{
		return fillColor;
	}

	@Override
	public void draw()
	{
		if(texture != null)
		{
			texture.active();
			texture.bind();
			super.draw();
			texture.unbind();
		}
		else
		{
			super.draw();
		}
	}
}
