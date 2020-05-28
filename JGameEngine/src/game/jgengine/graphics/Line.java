package game.jgengine.graphics;

import game.jgengine.entity.GameObject;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.glLineWidth;

public class Line extends GameObject
{
	private int width = 1;
	public Line()
	{
		super(new Mesh(new float[]{
				0f, 0f, 0f,		1,1,1,1,
				0f,0f,0f,		1,1,1,1,
		}, new int[]{
				0, 1
		}, Mesh.DIMENSION_3, Mesh.RGBA, Mesh.NO_TEXTURED), null);
		setPrimitive(GL_LINES);
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public void setBeginPosition(Vector3f pos)
	{
		getMesh().setPosition(0, pos);
	}

	public void setEndPosition(Vector3f pos)
	{
		getMesh().setPosition(1, pos);
	}

	@Override
	public void draw()
	{
		glLineWidth(width);
		super.draw();
	}
}
