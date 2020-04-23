package game.jgengine.graphics.shapes;

import game.jgengine.graphics.vertex.GraphicElement;
import game.jgengine.utils.Color;
import org.joml.Vector4f;

public class Shape extends GraphicElement
{
	protected int nbPoints = 0;

	protected Shape(int drawType)
	{
		super(drawType);
	}

	public Shape(float[] vertices, int[] indexes, int drawType)
	{
		super(vertices, indexes, drawType);
		nbPoints = vertices.length / 7;
	}

	public void setColor(Color color)
	{
		Vector4f cv = new Vector4f(color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio());
		for(int i = 0; i < nbPoints; i++)
		{
			vertexBuffer.setVertexColor(i, cv.x, cv.y, cv.z, cv.w);
		}
	}

	public void setColor(int pointIndex, Color color)
	{
		Vector4f cv = new Vector4f(color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio());
		vertexBuffer.setVertexColor(pointIndex, cv.x, cv.y, cv.z, cv.w);
	}

	public int getNbPoints()
	{
		return nbPoints;
	}

	protected void updateNbPoints()
	{
		nbPoints = vertexBuffer.getArray().length / 7;
	}
}
