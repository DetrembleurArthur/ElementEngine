package com.elemengine.graphics.vertex;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class Mesh
{
	public static final int COLOR = 4;
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

	public Mesh(float[] positions, float[] uvs, int[] indexes, int dimension, int uv)
	{
		float[] vertices = new float[positions.length + uvs.length];
		for(int i = 0; i < positions.length / dimension; i++)
		{
			for(int j = 0; j < dimension; j++)
			{
				vertices[i * (dimension + 2) + j] = positions[i * dimension + j];
			}
			vertices[i * (dimension + 2) + dimension] = uvs[i * 2];
			vertices[i * (dimension + 2) + dimension + 1] = uvs[i * 2 + 1];
		}
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
		vertexBuffer.setVertexPosition(index, vertexArray.vertexSize(), position.x, position.y, position.z);
	}

	public void setPosition(int index, Vector2f position)
	{
		vertexBuffer.setVertexPosition(index, vertexArray.vertexSize(), position.x, position.y);
	}

	public Vector2f getPosition(int index)
	{
		return vertexBuffer.getVertexPosition2(index, vertexArray.vertexSize());
	}

	public void setUV(int index, Vector2f uvs)
	{
		vertexBuffer.setVertexUV(index, vertexArray.vertexSize(), vertexArray.getPositionSize(), uvs);
	}

	public void destroy()
	{
		vertexArray.disableAttribs();
		vertexBuffer.unbind();
		vertexBuffer.destroy();
		indexBuffer.destroy();
		vertexArray.unbind();
		vertexArray.destroy();
	}
}
