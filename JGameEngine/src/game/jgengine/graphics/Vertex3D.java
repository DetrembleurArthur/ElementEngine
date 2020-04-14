package game.jgengine.graphics;

import static org.lwjgl.opengl.GL11.glVertex3f;

public class Vertex3D extends Vertex2D
{
	float z = 0.0f;

	public Vertex3D()
	{

	}

	public Vertex3D(float x, float y, float z)
	{
		super(x, y);
		this.z = z;
	}

	public Vertex3D(double x, double y, double z)
	{
		super(x, y);
		this.z = (float)z;
	}

	@Override
	public void draw()
	{
		glVertex3f(x, y, z);
	}
}
