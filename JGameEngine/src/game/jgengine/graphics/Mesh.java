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
	public static final int RGBA = 4;
	public static final int RGB = 3;
	public static final int NO_COLOR = 0;
	public static final int TEXTURED = 2;
	public static final int NO_TEXTURED = 0;
	private int n = 0;
	private int dimension = 0;
	private int colorDimension = 0;
	private int uv = 0;

	public Mesh(float[] vertices, int[] indexes, int dimension, int colorDimension, int uv)
	{
		initVertices(vertices, indexes,dimension,colorDimension,uv);
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
		n = vertices.length / (dimension + colorDimension + uv);
		this.dimension = dimension;
		this.colorDimension = colorDimension;
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

	public void setColor(Vector4f color)
	{
		for(int i = 0; i < n; i++)
		{
			vertexBuffer.setVertexColor(i, dimension + colorDimension + uv, dimension, color.x, color.y, color.z, color.w);
		}
	}

	public void setColor(int index, Vector4f color)
	{
		vertexBuffer.setVertexColor(index, dimension + colorDimension + uv, dimension, color.x, color.y, color.z, color.w);
	}

	public void setPosition(int index, Vector3f position)
	{
		vertexBuffer.setVertexPosition(index, dimension + colorDimension + uv, position.x, position.y, position.z);
	}

	public void setPosition(int index, Vector2f position)
	{
		vertexBuffer.setVertexPosition(index, dimension + colorDimension + uv, position.x, position.y);
	}

	public void destroy()
	{
		vertexArray.destroy();
		vertexBuffer.destroy();
		indexBuffer.destroy();
	}

}
