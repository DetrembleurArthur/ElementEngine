package game.jgengine.graphics.shapes;

import game.jgengine.entity.GameObject;
import game.jgengine.graphics.vertex.Mesh;
import game.jgengine.graphics.rendering.Texture;
import game.jgengine.utils.MathUtil;
import org.joml.Vector2f;

public abstract class Shape extends GameObject
{
	public Shape(Mesh mesh, Texture texture)
	{
		super(mesh, texture);
	}

	public void setSprite(Sprite sprite)
	{
		for(int i = 0; i < sprite.getTextCoords().length; i++)
		{
			getMesh().setUV(i, sprite.getTextCoords()[i]);
		}
	}


	public BoundingBox getBoundingBox()
	{
		var pos = getTopLeftPosition();
		var size = getSize();
		return new BoundingBox(pos.x, pos.y, size.x, size.y);
	}

	public boolean contains(Vector2f pos)
	{
		return getBoundingBox().isCollision(MathUtil.rotateAround(pos, getPosition2D(), -getRotation2D()));
	}
}
