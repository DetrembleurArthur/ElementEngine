package game.jgengine.graphics;

import game.jgengine.graphics.shaders.Shader;
import game.jgengine.graphics.vertex.IndexBuffer;
import game.jgengine.graphics.vertex.VertexArray;
import game.jgengine.graphics.vertex.VertexBuffer;
import game.jgengine.utils.Color;
import org.joml.Vector2f;

public class Mesh
{
	protected VertexArray vertexArray;
	protected VertexBuffer vertexBuffer;
	protected IndexBuffer indexBuffer;


	public Mesh(float[] vertices, int[] indexes)
	{
		initVertices(vertices, indexes);
	}

	protected void initVertices(float[] vertices, int[] indexes)
	{
		vertexArray = new VertexArray();
		vertexBuffer = new VertexBuffer(vertices);
		indexBuffer = new IndexBuffer(indexes);
		vertexBuffer.bind();
		indexBuffer.bind();
		vertexArray.initAttribs(3, 4);
		vertexBuffer.unbind();
		indexBuffer.unbind();
	}


	public void setVertices(float[] vertices)
	{
		vertexBuffer.update(0,vertices);
	}

	public void setIndexes(int[] indexes)
	{
		indexBuffer.update(indexes);
	}


	public VertexArray getVertexArray()
	{
		return vertexArray;
	}
	public VertexBuffer getVertexBuffer()
	{
		return vertexBuffer;
	}
	public IndexBuffer getIndexBuffer()
	{
		return indexBuffer;
	}

	public void addVertex(Vector2f position, Color color)
	{
		vertexBuffer.addVertex(position, color);
		indexBuffer.addIndex(indexBuffer.getLen());
		vertexBuffer.bind();
		indexBuffer.bind();
		vertexArray.initAttribs(3, 4);
		vertexBuffer.unbind();
		indexBuffer.unbind();
	}

	public void subVertex()
	{
		vertexBuffer.subVertex();
		indexBuffer.subIndex();
		vertexBuffer.bind();
		indexBuffer.bind();
		vertexArray.initAttribs(3, 4);
		vertexBuffer.unbind();
		indexBuffer.unbind();
	}

	public void destroy()
	{
		vertexArray.destroy();
		vertexBuffer.destroy();
		indexBuffer.destroy();
	}
}
