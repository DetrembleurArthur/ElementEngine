package game.jgengine.graphics;

import game.jgengine.graphics.shaders.Shader;
import game.jgengine.graphics.vertex.IndexBuffer;
import game.jgengine.graphics.vertex.VertexArray;
import game.jgengine.graphics.vertex.VertexBuffer;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Mesh
{
	protected VertexArray vertexArray;
	protected VertexBuffer vertexBuffer;
	protected IndexBuffer indexBuffer;
	public static final int DIMENSION_2 = 2;
	public static final int DIMENSION_3 = 3;
	public static final int TEXTURED = 2;
	public static final int NO_TEXTURED = 0;
	private int n = 0;
	private int dimension = 0;
	private int uv = 0;

	public Mesh(float[] vertices, int[] indexes, int dimension, int uv)
	{
		initVertices(vertices, indexes,dimension,uv);
	}



	protected void initVertices(float[] vertices, int[] indexes, int dimension, int uv)
	{
		vertexArray = new VertexArray();
		vertexBuffer = new VertexBuffer(vertices);
		indexBuffer = new IndexBuffer(indexes);
		vertexBuffer.bind();
		indexBuffer.bind();
		vertexArray.initAttribs(dimension,uv);
		vertexBuffer.unbind();
		indexBuffer.unbind();
		n = vertices.length / (dimension + uv);
		this.dimension = dimension;
		this.uv = uv;
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

	public int getN()
	{
		return n;
	}

	public void setPosition(int index, Vector3f position)
	{
		vertexBuffer.setVertexPosition(index, dimension + uv, position.x, position.y, position.z);
	}

	public void setPosition(int index, Vector2f position)
	{
		vertexBuffer.setVertexPosition(index, dimension + uv, position.x, position.y);
	}

	public Vector2f getPosition(int index)
	{
		return vertexBuffer.getVertexPosition2(index, dimension + uv);
	}

	public void setUV(int index, Vector2f uvs)
	{
		vertexBuffer.setVertexUV(index, dimension + uv, dimension, uvs);
	}

	public void destroy()
	{
		vertexArray.destroy();
		vertexBuffer.destroy();
		indexBuffer.destroy();
	}

}
