package game.jgengine.graphics;

import game.jgengine.utils.Color;
import game.jgengine.utils.Colors;

import static org.lwjgl.opengl.GL11.*;

public class VertexC2D extends Vertex2D
{
	private Color fillColor = Colors.BLACK;

	public VertexC2D()
	{
		super();
	}

	public VertexC2D(float x, float y, Color color)
	{
		super(x, y);
		fillColor = color;
	}

	public VertexC2D(double x, double y, Color color)
	{
		super(x, y);
		fillColor = color;
	}

	@Override
	public void draw()
	{
		glColor4f(fillColor.getRedRatio(), fillColor.getGreenRatio(), fillColor.getBlueRatio(), fillColor.getAlphaRatio());
		glVertex2f(x, y);
	}
}
