package game.jgengine.graphics.shapes;

import game.jgengine.entity.GameObject;
import game.jgengine.graphics.Mesh;
import game.jgengine.graphics.shaders.Texture;
import org.joml.Vector2f;
import org.joml.Vector3f;

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

	public Vector2f getDimension()
	{
		var scale = getScale();
		return new Vector2f(scale.x, scale.y);
	}

	private void setVerticesOrigin(float x, float y)
	{
		Mesh mesh = getMesh();
		mesh.setPosition(0, new Vector2f(0 + x, 0 + y));
		mesh.setPosition(1, new Vector2f(0 + x, 1 + y));
		mesh.setPosition(2, new Vector2f(1 + x, 1 + y));
		mesh.setPosition(3, new Vector2f(1 + x, 0 + y));
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
}
