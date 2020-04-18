package game.jgengine.graphics.shapes;

import game.jgengine.utils.Color;
import game.jgengine.utils.Vec2f;

import static org.lwjgl.opengl.GL11.*;

public class Line extends Shape
{
	private float width = 1f;

	protected Line(int drawType)
	{
		super(drawType);
	}

	public Line(Vec2f pos1, Vec2f pos2, Color color)
	{
		super(new float[]
		{
				pos1.x, pos1.y, 0f,		color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio(),
				pos2.x, pos2.y, 0f,		color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio()
		}, new int[]{0, 1}, GL_LINES);
	}

	public void setWidth(float w)
	{
		this.width = w;
	}

	public void setGradient(Color c1, Color c2)
	{
		vertexBuffer.setVertexColor(0, c1.getRedRatio(), c1.getGreenRatio(), c1.getBlueRatio(), c1.getAlphaRatio());
		vertexBuffer.setVertexColor(1, c2.getRedRatio(), c2.getGreenRatio(), c2.getBlueRatio(), c2.getAlphaRatio());
	}

	public void setColor(Color color)
	{
		setGradient(color, color);
	}

	public void setColor(int index, Color color)
	{
		vertexBuffer.setVertexColor(index, color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio());
	}

	public void setPosition(int index, Vec2f pos)
	{
		vertexBuffer.setVertexPosition(index, pos.x, pos.y, 0f);
	}

	@Override
	public void draw()
	{
		glLineWidth(width);
		super.draw();
		glLineWidth(1f);
	}

	public int getNbPoints()
	{
		return 2;
	}

	public float getWidth()
	{
		return width;
	}

	public static void draw(Vec2f pos1, Vec2f pos2)
	{
		glBegin(GL_LINES);
		glVertex2f(pos1.x, pos1.y);
		glVertex2f(pos2.x, pos2.y);
		glEnd();
	}

	public static void draw(Vec2f pos1, Vec2f pos2, Color color)
	{
		glBegin(GL_LINES);
		glColor4f(color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio());
		glVertex2f(pos1.x, pos1.y);
		glVertex2f(pos2.x, pos2.y);
		glEnd();
	}

	public static void draw(Vec2f pos1, Vec2f pos2, Color color1, Color color2)
	{
		glBegin(GL_LINES);
		glColor4f(color1.getRedRatio(), color1.getGreenRatio(), color1.getBlueRatio(), color1.getAlphaRatio());
		glVertex2f(pos1.x, pos1.y);
		glColor4f(color2.getRedRatio(), color2.getGreenRatio(), color2.getBlueRatio(), color2.getAlphaRatio());
		glVertex2f(pos2.x, pos2.y);
		glEnd();
	}

	public static void draw(Vec2f pos1, Vec2f pos2, int weight)
	{
		glLineWidth(weight);
		draw(pos1, pos2);
		glLineWidth(1);
	}

	public static void draw(Vec2f pos1, Vec2f pos2, Color color, int weight)
	{
		glLineWidth(weight);
		draw(pos1, pos2, color);
		glLineWidth(1);
	}

	public static void draw(Vec2f pos1, Vec2f pos2, Color color1, Color color2, int weight)
	{
		glLineWidth(weight);
		draw(pos1, pos2, color1, color2);
		glLineWidth(1);
	}
}
