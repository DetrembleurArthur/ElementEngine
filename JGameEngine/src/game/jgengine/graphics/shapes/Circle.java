package game.jgengine.graphics.shapes;

import game.jgengine.debug.Logs;
import game.jgengine.graphics.vertex.Mesh;
import game.jgengine.graphics.rendering.Texture;
import game.jgengine.utils.MathUtil;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Circle extends Shape
{
	private int nPoints;
	private Vector2f glCenter;

	public Circle(float radius, int points, Texture texture)
	{
		super(null, texture);
		this.nPoints = points;
		setRadius(radius);
		glCenter = new Vector2f(0.5f, 0.5f);
		build();
	}

	public void setRadius(float radius)
	{
		setScale(new Vector3f(radius * 2, radius * 2, 0));
	}

	public float getRadius()
	{
		return (getScale2D().x/2 + getScale2D().y/2) / 2f;
	}


	public void build()
	{
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



	@Override
	public Vector2f getTopLeftPosition()
	{
		var mesh = getMesh();
		var len = mesh.getN();
		var pos = mesh.getPosition(0);
		for(int i = 1; i < len; i++)
		{
			var mpos = mesh.getPosition(i);
			if(pos.x > mpos.x) pos.x = mpos.x;
			if(pos.y > mpos.y) pos.y = mpos.y;
		}
		return new Vector2f(position.x + scale.x * pos.x, position.y + scale.y * pos.y);
	}

	public boolean isClosed(Vector2f point)
	{
		//Logs.print(getCenter().x + ", " + getCenter().y);
		return getCenter().distance(point) <= getRadius();
	}


	@Override
	protected void setVerticesOrigin(float x, float y)
	{
		glCenter.x = 0.5f + x;
		glCenter.y = 0.5f + y;
		build();
	}
}
