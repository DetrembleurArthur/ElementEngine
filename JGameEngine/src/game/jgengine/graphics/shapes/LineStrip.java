package game.jgengine.graphics.shapes;

import game.jgengine.graphics.VertexArray;
import game.jgengine.utils.Color;
import game.jgengine.utils.Vec2f;

import static org.lwjgl.opengl.GL11.GL_LINE_STRIP;

public class LineStrip extends Line
{
	public LineStrip(Vec2f[] points, Color color)
	{
		super(GL_LINE_STRIP);
		float[] vertices = new float[points.length * 7];
		int [] indexes = new int[points.length];
		for(int i = 0; i < points.length; i++)
		{
			vertices[i * 7] = points[i].x;
			vertices[i * 7 + 1] = points[i].y;
			vertices[i * 7 + 2] = 0f;
			vertices[i * 7 + 3] = color.getRedRatio();
			vertices[i * 7 + 4] = color.getGreenRatio();
			vertices[i * 7 + 5] = color.getBlueRatio();
			vertices[i * 7 + 6] = color.getAlphaRatio();
			indexes[i] = i;
		}
		initVertices(vertices, indexes);
	}

	@Override
	public void setGradient(Color c1, Color c2)
	{
		var len = indexBuffer.getLen();
		for(int i = 0; i < len; i++)
		{
			setColor(i, i % 2 == 0 ? c1 : c2);
		}
	}

	@Override
	public int getNbPoints()
	{
		return indexBuffer.getLen();
	}

	public void addPoint(Vec2f pos, Color c)
	{
		var vertices = vertexBuffer.getArray();
		float[] newVertices = new float[vertices.length + 7];
		for(int i = 0; i < vertices.length; i++) newVertices[i] = vertices[i];
		newVertices[vertices.length] = pos.x;
		newVertices[vertices.length + 1] = pos.y;
		newVertices[vertices.length + 2] = 0f;
		newVertices[vertices.length + 3] = c.getRedRatio();
		newVertices[vertices.length + 4] = c.getGreenRatio();
		newVertices[vertices.length + 5] = c.getBlueRatio();
		newVertices[vertices.length + 6] = c.getAlphaRatio();
		var indexes = indexBuffer.getArray();
		int[] newIndexes = new int[indexes.length + 1];
		for(int i = 0; i < indexes.length; i++) newIndexes[i] = indexes[i];
		newIndexes[indexes.length] = indexes.length;
		reloadVertices(newVertices, newIndexes);
	}

	public void subPoint()
	{
		if(getNbPoints() > 0)
		{
			var vertices = vertexBuffer.getArray();
			float[] newVertices = new float[vertices.length - 7];
			for(int i = 0; i < vertices.length - 7; i++) newVertices[i] = vertices[i];
			var indexes = indexBuffer.getArray();
			int[] newIndexes = new int[indexes.length - 1];
			for(int i = 0; i < indexes.length - 1; i++) newIndexes[i] = indexes[i];
			reloadVertices(newVertices, newIndexes);
		}
	}
}
