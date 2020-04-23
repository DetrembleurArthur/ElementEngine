package game.jgengine.graphics.shapes;

import game.jgengine.utils.Color;
import org.joml.Vector2f;
import org.joml.Vector4f;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;

public class Rectangle extends Shape
{
	public Rectangle(Vector2f pos, Vector2f size, Color color)
	{
		super(new float[]
		{
				pos.x, pos.y, 0f, color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio(),
				pos.x, pos.y + size.y, 0f, color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio(),
				pos.x + size.x, pos.y + size.y, 0f, color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio(),
				pos.x + size.x, pos.y, 0f, color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio()
		}
		, new int[]
		{
				0, 1, 2,
				2, 3, 0
		}, GL_TRIANGLES);
	}

	public void setHorizontalGradient(Color color, Color color2)
	{
		Vector4f cv = new Vector4f(color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio());
		vertexBuffer.setVertexColor(0, cv.x, cv.y, cv.z, cv.w);
		vertexBuffer.setVertexColor(1, cv.x, cv.y, cv.z, cv.w);
		cv = new Vector4f(color2.getRedRatio(), color2.getGreenRatio(), color2.getBlueRatio(), color2.getAlphaRatio());
		vertexBuffer.setVertexColor(2, cv.x, cv.y, cv.z, cv.w);
		vertexBuffer.setVertexColor(3, cv.x, cv.y, cv.z, cv.w);
	}

	public void setVerticalGradient(Color color, Color color2)
	{
		Vector4f cv = new Vector4f(color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio());
		vertexBuffer.setVertexColor(0, cv.x, cv.y, cv.z, cv.w);
		vertexBuffer.setVertexColor(3, cv.x, cv.y, cv.z, cv.w);
		cv = new Vector4f(color2.getRedRatio(), color2.getGreenRatio(), color2.getBlueRatio(), color2.getAlphaRatio());
		vertexBuffer.setVertexColor(2, cv.x, cv.y, cv.z, cv.w);
		vertexBuffer.setVertexColor(1, cv.x, cv.y, cv.z, cv.w);
	}

	public void setGradient(Color color, Color color2, Color color3, Color color4)
	{
		Vector4f cv = new Vector4f(color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio());
		vertexBuffer.setVertexColor(0, cv.x, cv.y, cv.z, cv.w);
		cv = new Vector4f(color2.getRedRatio(), color2.getGreenRatio(), color2.getBlueRatio(), color2.getAlphaRatio());
		vertexBuffer.setVertexColor(1, cv.x, cv.y, cv.z, cv.w);
		cv = new Vector4f(color3.getRedRatio(), color3.getGreenRatio(), color3.getBlueRatio(), color3.getAlphaRatio());
		vertexBuffer.setVertexColor(2, cv.x, cv.y, cv.z, cv.w);
		cv = new Vector4f(color4.getRedRatio(), color4.getGreenRatio(), color4.getBlueRatio(), color4.getAlphaRatio());
		vertexBuffer.setVertexColor(3, cv.x, cv.y, cv.z, cv.w);
	}
}
