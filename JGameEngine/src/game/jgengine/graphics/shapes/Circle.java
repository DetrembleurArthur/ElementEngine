package game.jgengine.graphics.shapes;

import game.jgengine.debug.Logs;
import game.jgengine.entity.GameObject;
import game.jgengine.graphics.vertex.Mesh;
import game.jgengine.graphics.rendering.Texture;
import game.jgengine.utils.MathUtil;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Circle extends GameObject
{
	private int nPoints;

	public Circle(float radius, int points, Texture texture)
	{
		super(null, texture);
		this.nPoints = points;
		setRadius(radius);
		build();
	}

	public void setRadius(float radius)
	{
		setSize(radius * 2, radius * 2);
	}

	public float getRadius()
	{
		return (getWidth() + getHeight()) / 2f;
	}


	public void build()
	{
		Vector2f glCenter = new Vector2f(0.5f, 0.5f);
		float angle = (float)Math.toRadians(360f / nPoints);
		float[] vertex = new float[(nPoints + 1) * 4];
		vertex[0] = glCenter.x;
		vertex[1] = glCenter.y;
		vertex[2] = glCenter.x;
		vertex[3] = glCenter.y;
		int j = 4;
		for(int i = 0; i < nPoints; i++)
		{
			vertex[j++] = (float) (glCenter.x + (0.5 * Math.sin(angle * i)));
			vertex[j++] = (float) (glCenter.y + (-0.5 * Math.cos(angle * i)));
			vertex[j++] = vertex[j-3];
			vertex[j++] = vertex[j-3];
		}
		int[] indexes = new int[nPoints * 3];
		j = 0;
		for(int i = 0; i < nPoints; i++)
		{
			indexes[j++] = i+1;
			if(i + 2 > nPoints)
				indexes[j++] = 1;
			else
				indexes[j++] = i + 2;
			indexes[j++] = 0;
		}
		setMesh(new Mesh(vertex, indexes, Mesh.DIMENSION_2, Mesh.TEXTURED));
	}

	public int getnPoints()
	{
		return nPoints;
	}

	public void setnPoints(int nPoints)
	{
		this.nPoints = nPoints;
		build();
	}

	public boolean isClosed(Vector2f point)
	{
		return getCenterPosition().distance(point) <= getRadius();
	}
}
