package game.jgengine.graphics.shapes;

import game.jgengine.utils.Color;
import org.joml.Vector2f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glLineWidth;

public class LineStrip extends Line
{
	public LineStrip(Vector2f[] points, Color color)
	{
		super(GL_LINE_STRIP);
		float[] vertices = new float[points.length * 7];
		int [] indexes = new int[points.length];
		for(int i = 0; i < points.length; i++)
		{
			vertices[i * 7] = points[i].x;
			vertices[i * 7 + 1] = points[i].y;
			vertices[i * 7 + 2] = 0f;
			vertices[i * 7 + 3] = color.getRedRatio();
			vertices[i * 7 + 4] = color.getGreenRatio();
			vertices[i * 7 + 5] = color.getBlueRatio();
			vertices[i * 7 + 6] = color.getAlphaRatio();
			indexes[i] = i;
		}
		initVertices(vertices, indexes);
		updateNbPoints();
	}

	@Override
	public void setGradient(Color c1, Color c2)
	{
		var len = indexBuffer.getLen();
		for(int i = 0; i < len; i++)
		{
			setColor(i, i % 2 == 0 ? c1 : c2);
		}
	}

	@Override
	public int getNbPoints()
	{
		return indexBuffer.getLen();
	}

	public void addPoint(Vector2f pos, Color c)
	{
		addVertex(pos, c);
	}

	public void subPoint()
	{
		subVertex();
	}



	public static void draw(Vector2f[] points)
	{
		glBegin(GL_LINE_STRIP);
		for(Vector2f point : points)
		{
			glVertex2f(point.x, point.y);
		}
		glEnd();
	}

	public static void draw(Vector2f[] points, Color color)
	{
		glBegin(GL_LINE_STRIP);
		glColor4f(color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio());
		for(Vector2f point : points)
		{
			glVertex2f(point.x, point.y);
		}
		glEnd();
	}

	public static void draw(Vector2f[] points, Color color1, Color color2)
	{
		boolean altern = false;
		glBegin(GL_LINE_STRIP);
		for(Vector2f point : points)
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

	public static void draw(Vector2f[] points, int weight)
	{
		glLineWidth(weight);
		draw(points);
		glLineWidth(1);
	}

	public static void draw(Vector2f[] points, Color color, int weight)
	{
		glLineWidth(weight);
		draw(points, color);
		glLineWidth(1);
	}

	public static void draw(Vector2f[] points, Color color1, Color color2, int weight)
	{
		glLineWidth(weight);
		draw(points, color1, color2);
		glLineWidth(1);
	}
}
