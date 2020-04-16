package game.jgengine.graphics.shapes;

import game.jgengine.utils.Color;
import game.jgengine.utils.Vec2f;

import static org.lwjgl.opengl.GL11.GL_LINE_LOOP;

public class LineLoop extends LineStrip
{
	public LineLoop(Vec2f[] points, Color color)
	{
		super(points, color);
		drawType = GL_LINE_LOOP;
	}
}
