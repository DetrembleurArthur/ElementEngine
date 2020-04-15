package game.jgengine.graphics.shapes;

import game.jgengine.graphics.Vertices;
import game.jgengine.utils.Vec2f;
import game.jgengine.utils.Vec2i;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;

public class Triangle extends Shape
{
	public Triangle(float p1x, float p1y, float p2x, float p2y, float p3x, float p3y)
	{
		vertices = new Vertices(new float[]
				{
						p1x, p1y, 0.f,	1.f, 0.f, 0.0f, 1.f,
						p2x, p2y, 0.f,	0.f, 1.f, 0.f, 1.f,
						p3x, p3y, 0.f,	0f, 0f, 1.f, 1.f
				}, new int[]{0, 1, 2});
	}

	@Override
	public void draw()
	{
		vertices.draw(GL_TRIANGLES);
	}
}
