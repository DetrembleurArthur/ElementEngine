package game.jgengine.graphics.shapes;

import game.jgengine.graphics.Mesh;
import game.jgengine.graphics.vertex.GraphicElement;
import game.jgengine.utils.Color;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Shape extends GraphicElement
{
	protected int nbPoints = 0;


	public Shape(Mesh mesh, int drawType)
	{
		super(new Vector3f(), new Vector3f(), new Vector3f(), mesh, drawType);
		nbPoints = mesh.getVertexBuffer().getArray().length / 7;
	}

	public void setColor(Color color)
	{
		Vector4f cv = new Vector4f(color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio());
		for(int i = 0; i < nbPoints; i++)
		{
			getMesh().getVertexBuffer().setVertexColor(i, cv.x, cv.y, cv.z, cv.w);
		}
	}

	public void setColor(int pointIndex, Color color)
	{
		Vector4f cv = new Vector4f(color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio());
		getMesh().getVertexBuffer().setVertexColor(pointIndex, cv.x, cv.y, cv.z, cv.w);
	}

	public int getNbPoints()
	{
		return nbPoints;
	}

	protected void updateNbPoints()
	{
		nbPoints = getMesh().getVertexBuffer().getArray().length / 7;
	}
}
