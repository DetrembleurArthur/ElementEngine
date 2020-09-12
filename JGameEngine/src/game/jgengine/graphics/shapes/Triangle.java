package game.jgengine.graphics.shapes;

import game.jgengine.entity.GameObject;
import game.jgengine.graphics.rendering.Texture;
import game.jgengine.graphics.vertex.Mesh;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Triangle extends GameObject
{

	public Triangle(Texture texture)
	{
		super(Triangle.build(new Vector2f(0.5f, 0f), new Vector2f(0f, 1f), new Vector2f(1f, 1f)), texture);
	}

	public void setPoint(int index, Vector2f point)
	{
		if(point.x <= 1f && point.y <= 1f)
		{
			getMesh().setPosition(index, point);
			getMesh().setUV(index, point);
		}
	}

	public void setPoints(Vector2f p1, Vector2f p2, Vector2f p3)
	{
		setPoint(0, p1);
		setPoint(1, p2);
		setPoint(2, p3);
	}

	public static Mesh build(Vector2f p1, Vector2f p2, Vector2f p3)
	{

		float[] vertices = new float[]
		{
				p1.x, p1.y, p1.x, p1.y,
				p2.x, p2.y, p2.x, p2.y,
				p3.x, p3.y, p3.x, p3.y
		};
		int[] indexes = new int[]
		{
				0, 1, 2
		};
		return new Mesh(vertices, indexes, Mesh.DIMENSION_2, Mesh.TEXTURED);
	}
}
