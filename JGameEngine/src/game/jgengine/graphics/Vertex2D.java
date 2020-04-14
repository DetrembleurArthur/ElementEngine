package game.jgengine.graphics;

import static org.lwjgl.opengl.GL11.glVertex2f;

public class Vertex2D implements Vertex
{
	public float x = 0.0f;
	public float y = 0.0f;

	public Vertex2D()
	{

	}

	public Vertex2D(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public Vertex2D(double x, double y)
	{
		this.x = (float)x;
		this.y = (float)y;
	}

	@Override
	public void draw()
	{
		glVertex2f(x, y);
	}
}
