package game.jgengine.graphics.shapes;

import game.jgengine.graphics.rendering.Texture;
import game.jgengine.graphics.vertex.Mesh;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Triangle extends Shape
{
	private Vector2f[] base;
	private Vector2f origin;

	public Triangle(Texture texture)
	{
		super(Triangle.build(new Vector2f(0.5f, 0f), new Vector2f(0f, 1f), new Vector2f(1f, 1f)), texture);
		origin = new Vector2f();
		updateBase();
	}

	private void updateBase()
	{
		base = new Vector2f[3];
		base[0] = getMesh().getPosition(0);
		base[1] = getMesh().getPosition(1);
		base[2] = getMesh().getPosition(2);
	}

	public void setPoint(int index, Vector2f point)
	{
		if(point.x < 1f && point.y < 1f)
		{
			getMesh().setPosition(index, new Vector2f(point).add(origin));
			getMesh().setUV(index, new Vector2f(point).add(origin));
			base[index] = new Vector2f(point);
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

	public Vector2f[] getPoints()
	{
		Vector2f[] points = new Vector2f[3];
		points[0] = getMesh().getPosition(0);
		points[1] = getMesh().getPosition(1);
		points[2] = getMesh().getPosition(2);
		return points;
	}

	@Override
	protected void setVerticesOrigin(float x, float y)
	{
		origin = new Vector2f(x, y);
		setMesh(Triangle.build(new Vector2f(base[0]).add(x, y), new Vector2f(base[1]).add(x, y), new Vector2f(base[2]).add(x, y)));
	}

	@Override
	public Vector2f getTopLeftPosition()
	{
		Vector2f pos = getMesh().getPosition(0);
		for(int i = 1; i < getMesh().getN(); i++)
		{
			var mpos = getMesh().getPosition(i);
			if(pos.x > mpos.x) pos.x = mpos.x;
			if(pos.y > mpos.y) pos.y = mpos.y;
		}
		return pos.mul(getSize()).add(getPosition2D());
	}

	@Override
	public void setTopLeftPosition(Vector2f pos)
	{
		setPosition(pos.sub(new Vector2f(origin).mul(getSize())));
	}
}
