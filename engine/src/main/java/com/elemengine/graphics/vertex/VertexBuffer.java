package com.elemengine.graphics.vertex;


import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.system.MemoryUtil.memFree;

public class VertexBuffer
{
	private int vbo = 0;

	public VertexBuffer(float[] vertices)
	{
		initVbo();
		initVertices(vertices);
	}

	private static FloatBuffer initBuffer(float[] vertices)
	{
		FloatBuffer buffer = MemoryUtil.memAllocFloat(vertices.length);
		buffer.put(vertices).flip();
		return buffer;
	}

	private void initVbo()
	{
		vbo = glGenBuffers();
	}

	public void bind()
	{
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
	}

	public void unbind()
	{
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public void initVertices(float[] vertices)
	{
		FloatBuffer buffer = initBuffer(vertices);
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_DYNAMIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		if(buffer != null)
			memFree(buffer);
	}

	public void update(float[] vertices)
	{
		update(initBuffer(vertices));
	}

	public void update(FloatBuffer buffer)
	{
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferSubData(GL_ARRAY_BUFFER, 0, buffer);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		if(buffer != null)
			memFree(buffer);
	}


	public void update(int offset, float[] fbuffer)
	{
		update(offset, initBuffer(fbuffer));
	}

	public void update(int offset, FloatBuffer buffer)
	{
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferSubData(GL_ARRAY_BUFFER, offset, buffer);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		if(buffer != null)
			memFree(buffer);
	}

	public void setVertexPosition(int index, int vertexSize, float x, float y, float z)
	{
		update(index * vertexSize * Float.BYTES, initBuffer(new float[]{x, y, z}));
	}

	public void setVertexPosition(int index, int vertexSize, float x, float y)
	{
		update(index * vertexSize * Float.BYTES, initBuffer(new float[]{x, y}));
	}

	public Vector2f getVertexPosition2(int index, int vertexSize)
	{
		float[] buffer = new float[2];
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glGetBufferSubData(GL_ARRAY_BUFFER, index * vertexSize * Float.BYTES, buffer);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		return new Vector2f(buffer[0], buffer[1]);
	}

	public Vector3f getVertexPosition3(int index, int vertexSize)
	{
		float[] buffer = new float[3];
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glGetBufferSubData(GL_ARRAY_BUFFER, index * vertexSize * Float.BYTES, buffer);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		return new Vector3f(buffer[0], buffer[1], buffer[2]);
	}

	public void setVertexUV(int index, int vertexSize, int offset, Vector2f uv)
	{
		update(index * vertexSize * Float.BYTES + offset * Float.BYTES, initBuffer(new float[]{uv.x, uv.y}));
	}

	public void destroy()
	{
		glDeleteBuffers(vbo);
	}
}
