package game.jgengine.entity;

import game.jgengine.graphics.GraphicElement;
import game.jgengine.graphics.Mesh;
import game.jgengine.graphics.shaders.Texture;
import game.jgengine.utils.Colors;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.system.CallbackI;

import static org.lwjgl.opengl.GL11.glLineWidth;

public class GameObject extends GraphicElement
{
	private Texture texture = null;
	private Vector4f fillColor = Colors.WHITE;

	public GameObject(Mesh mesh, Texture texture)
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

	public void setOpacity(float value)
	{
		if(fillColor != null)
			fillColor.w = value;
	}

	public float getOpacity()
	{
		return fillColor.w;
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
