package game.jgengine.graphics.shapes;

import game.jgengine.graphics.*;
import game.jgengine.utils.DPoint2D;

import static org.lwjgl.opengl.GL11.*;

public class Point extends Shape
{
	public Point()
	{
		position = new DPoint2D(0.0, 0.0);
		updateVerteces();
	}

	public Point(double x, double y)
	{
		position = new DPoint2D(x, y);
		updateVerteces();
	}

	public Point(DPoint2D position)
	{
		this.position = position;
		updateVerteces();
	}

	@Override
	protected void updateVerteces()
	{
		verteces = new VertexArray(new Vertex[]{new Vertex2D(position.getX(), position.getY())});
	}

	@Override
	public void draw()
	{
		glBegin(GL_POINTS);
		verteces.draw();
		glEnd();
	}
}
