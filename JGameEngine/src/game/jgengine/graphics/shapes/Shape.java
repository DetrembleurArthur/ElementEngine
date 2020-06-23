package game.jgengine.graphics.shapes;

import game.jgengine.entity.GameObject;
import game.jgengine.graphics.vertex.Mesh;
import game.jgengine.graphics.rendering.Texture;
import org.joml.Vector2f;

public abstract class Shape extends GameObject
{
	public Shape(Mesh mesh, Texture texture)
	{
		super(mesh, texture);
	}

	protected abstract void setVerticesOrigin(float x, float y);
	public void setSprite(Sprite sprite)
	{
		for(int i = 0; i < sprite.getTextCoords().length; i++)
		{
			getMesh().setUV(i, sprite.getTextCoords()[i]);
		}
	}

	public void setOrigin(float x, float y)
	{
		setVerticesOrigin(-x / getScale().x, -y / getScale().y);
	}

	public Vector2f getOrigin()
	{
		return getMesh().getPosition(0);
	}

	public void setOrigin(Vector2f origin)
	{
		setOrigin(origin.x, origin.y);
	}

	public void setTopLeftOrigin()
	{
		setOrigin(0, 0);
	}

	public void setTopRightOrigin()
	{
		setOrigin(getScale().x, 0);
	}

	public void setBottomLeftOrigin()
	{
		setOrigin(0, getScale().y);
	}

	public void setBottomRightOrigin()
	{
		setOrigin(getScale().x, getScale().y);
	}

	public void setCenterOrigin()
	{
		setOrigin(getScale().x / 2, getScale().y / 2);
	}

	public BoundingBox getBoundingBox()
	{
		var pos = getPosition2D();
		var size = getScale();
		return new BoundingBox(pos.x, pos.y, size.x, size.y);
	}
}
