package game.jgengine.graphics.shapes;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class BoundingBox
{
	private float x;
	private float y;
	private float width;
	private float height;

	public BoundingBox(float x, float y, float width, float height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}

	public float getWidth()
	{
		return width;
	}

	public float getHeight()
	{
		return height;
	}

	public boolean isCollision(final BoundingBox other)
	{
		return x < other.x + other.width && x + width > other.x && y < other.y + other.height && y + height > other.y;
	}

	public Rectangle asRectangle()
	{
		var r = new Rectangle(null);
		r.setPosition(new Vector2f(x, y));
		r.setScale(new Vector3f(width, height, 1));
		return r;
	}
}
