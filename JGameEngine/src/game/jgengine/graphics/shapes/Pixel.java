package game.jgengine.graphics.shapes;

import game.jgengine.graphics.Vertex;
import game.jgengine.utils.Color;
import game.jgengine.utils.Vec2f;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class Pixel
{
	private Vec2f pos;
	private Color color;

	public Pixel(Vec2f pos, Color color)
	{
		this.pos = pos;
		this.color = color;
	}

	public void draw()
	{
		glBegin(GL_POINTS);
		glColor4f(color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio());
		glVertex3f(pos.x, pos.y, 0f);
		glEnd();
	}

	public void setPosition(Vec2f pos)
	{
		this.pos = pos;


	}

	public void setColor(Color color)
	{
		this.color = color;
	}
}
