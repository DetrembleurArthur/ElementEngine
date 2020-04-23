package game.jgengine.graphics;

import game.jgengine.utils.Color;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Graphics
{
	public static void setColor(Color color)
	{
		glColor4f(color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio());
	}
}
