package game.jgengine.graphics.shapes;

import game.jgengine.entity.GameObject;
import game.jgengine.graphics.Mesh;
import game.jgengine.graphics.shaders.Texture;
import org.joml.Vector2f;

public class Rectangle extends GameObject
{
	public Rectangle(Texture texture)
	{
		super(new Mesh(
				new float[]{
						0, 0,	0, 0,
						0, 1,	0, 1,
						1, 1,	1, 1,
						1, 0,	1, 0
				},
				new int[]{
						0, 1, 2, 0, 2, 3
				},Mesh.DIMENSION_2, Mesh.TEXTURED

		), texture);
		if(texture != null)
			setDimension(texture.getDimension());
	}

	public void setWidth(float width)
	{
		getScale().x = width;
	}

	public void setHeight(float height)
	{
		getScale().y = height;
	}

	public void setDimension(Vector2f dimension)
	{
		getScale().x = dimension.x;
		getScale().y = dimension.y;
	}

	public void setDimension(float x, float y)
	{
		getScale().x = x;
		getScale().y = y;
	}
}
