package game.jgengine.graphics.shapes;

import game.jgengine.utils.Color;
import org.joml.Vector2f;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;

public class Triangle extends Shape
{
	public Triangle(Vector2f p1, Vector2f p2, Vector2f p3, Color color)
	{
		super(new float[]{
				p1.x, p1.y, 0f, color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio(),
				p2.x, p2.y, 0f, color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio(),
				p3.x, p3.y, 0f, color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio()
		}, new int[]{0,1,2}, GL_TRIANGLES);
	}
}
