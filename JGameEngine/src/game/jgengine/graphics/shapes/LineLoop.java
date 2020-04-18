package game.jgengine.graphics.shapes;

import game.jgengine.utils.Color;
import game.jgengine.utils.Vec2f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glLineWidth;

public class LineLoop extends LineStrip
{
	public LineLoop(Vec2f[] points, Color color)
	{
		super(points, color);
		setDrawType(GL_LINE_LOOP);
	}

	public static void draw(Vec2f[] points)
	{
		glBegin(GL_LINE_LOOP);
		for(Vec2f point : points)
		{
			glVertex2f(point.x, point.y);
		}
		glEnd();
	}

	public static void draw(Vec2f[] points, Color color)
	{
		glBegin(GL_LINE_LOOP);
		glColor4f(color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio());
		for(Vec2f point : points)
		{
			glVertex2f(point.x, point.y);
		}
		glEnd();
	}

	public static void draw(Vec2f[] points, Color color1, Color color2)
	{
		boolean altern = false;
		glBegin(GL_LINE_LOOP);
		for(Vec2f point : points)
		{
			if (altern)
			{
				glColor4f(color2.getRedRatio(), color2.getGreenRatio(), color2.getBlueRatio(), color2.getAlphaRatio());
			}
			else
			{
				glColor4f(color1.getRedRatio(), color1.getGreenRatio(), color1.getBlueRatio(), color1.getAlphaRatio());
			}
			glVertex2f(point.x, point.y);
			altern = !altern;
		}
		glEnd();
	}

	public static void draw(Vec2f[] points, int weight)
	{
		glLineWidth(weight);
		draw(points);
		glLineWidth(1);
	}

	public static void draw(Vec2f[] points, Color color, int weight)
	{
		glLineWidth(weight);
		draw(points, color);
		glLineWidth(1);
	}

	public static void draw(Vec2f[] points, Color color1, Color color2, int weight)
	{
		glLineWidth(weight);
		draw(points, color1, color2);
		glLineWidth(1);
	}
}
