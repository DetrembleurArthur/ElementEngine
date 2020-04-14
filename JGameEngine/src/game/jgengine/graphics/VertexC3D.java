package game.jgengine.graphics;

import game.jgengine.utils.Color;
import game.jgengine.utils.Colors;

import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glVertex3f;

public class VertexC3D extends Vertex3D
{
	private Color fillColor = Colors.BLACK;

	public VertexC3D()
	{
		super();
	}

	public VertexC3D(float x, float y, float z, Color color)
	{
		super(x, y, z);
		fillColor = color;
	}

	public VertexC3D(double x, double y, double z, Color color)
	{
		super(x, y, z);
		fillColor = color;
	}

	@Override
	public void draw()
	{
		glColor4f(fillColor.getRedRatio(), fillColor.getGreenRatio(), fillColor.getBlueRatio(), fillColor.getAlphaRatio());
		glVertex3f(x, y, z);
	}
}
