package game.jgengine.graphics.shapes;

import game.jgengine.utils.Color;
import org.joml.Vector2f;

import static org.lwjgl.opengl.GL11.*;

public class Pixel extends Shape
{
	public Pixel(Vector2f position, Color color)
	{
		super(new float[]{
				position.x, position.y, 0f, color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio()
		}, new int[]{0}, GL_POINTS);
	}

	public static void draw(Vector2f position)
	{
		glBegin(GL_POINTS);
		glVertex2f(position.x, position.y);
		glEnd();
	}

	public static void draw(Vector2f position, Color color)
	{
		glBegin(GL_POINTS);
		glColor4f(color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio());
		glVertex2f(position.x, position.y);
		glEnd();
	}
}
