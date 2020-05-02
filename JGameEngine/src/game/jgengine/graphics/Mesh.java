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
	public static int DIMENSION_2 = 2;
	public static int DIMENSION_3 = 3;
	public static int RGBA = 4;
	public static int RGB = 3;


	public Mesh(float[] vertices, int[] indexes, int dimension)
	{
		initVertices(vertices, indexes,dimension);
	}

	public Mesh(float[] vertices, int[] indexes, int dimension, int colorDimension)
	{
		initVertices(vertices, indexes,dimension,colorDimension);
	}

	public Mesh(float[] vertices, int[] indexes, int dimension, int colorDimension, int uv)
	{
		initVertices(vertices, indexes,dimension,colorDimension,uv);
	}

	protected void initVertices(float[] vertices, int[] indexes, int dimension)
	{
		vertexArray = new VertexArray();
		vertexBuffer = new VertexBuffer(vertices);
		indexBuffer = new IndexBuffer(indexes);
		vertexBuffer.bind();
		indexBuffer.bind();
		vertexArray.initAttribs(dimension);
		vertexBuffer.unbind();
		indexBuffer.unbind();
	}

	protected void initVertices(float[] vertices, int[] indexes, int dimension, int colorDimension)
	{
		vertexArray = new VertexArray();
		vertexBuffer = new VertexBuffer(vertices);
		indexBuffer = new IndexBuffer(indexes);
		vertexBuffer.bind();
		indexBuffer.bind();
		vertexArray.initAttribs(dimension, colorDimension);
		vertexBuffer.unbind();
		indexBuffer.unbind();
	}

	protected void initVertices(float[] vertices, int[] indexes, int dimension, int colorDimension, int uv)
	{
		vertexArray = new VertexArray();
		vertexBuffer = new VertexBuffer(vertices);
		indexBuffer = new IndexBuffer(indexes);
		vertexBuffer.bind();
		indexBuffer.bind();
		vertexArray.initAttribs(dimension, colorDimension,uv);
		vertexBuffer.unbind();
		indexBuffer.unbind();
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


	public void destroy()
	{
		vertexArray.destroy();
		vertexBuffer.destroy();
		indexBuffer.destroy();
	}
}
