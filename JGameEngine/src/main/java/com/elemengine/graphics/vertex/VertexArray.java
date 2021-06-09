package com.elemengine.graphics.vertex;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

public class VertexArray
{
	private int vao = 0;
	private byte attribs = 2;
	private byte positionSize = 0;
	private byte uvSize = 0;

	public VertexArray()
	{
		vao = glGenVertexArrays();
		bind();
	}


	public void initAttribs(int dimension, int uv)
	{
		if(dimension != 2 && dimension != 3)
			dimension = 3;

		positionSize = (byte)dimension;
		uvSize = (byte)uv;
		int vertexSize = (positionSize + uv) * Float.BYTES;
		glVertexAttribPointer(0, positionSize, GL_FLOAT, true, vertexSize, 0);
		glEnableVertexAttribArray(0);
		glVertexAttribPointer(1, uvSize, GL_FLOAT, true, vertexSize, positionSize * Float.BYTES);
		glEnableVertexAttribArray(1);
	}
	public void bind()
	{
		glBindVertexArray(vao);
	}

	public void unbind()
	{
		glBindVertexArray(0);
	}

	public void enableAttrib(int index)
	{
		glEnableVertexAttribArray(index);
	}

	public void enableAttribs()
	{
		for(int i = 0; i < attribs; i++)
		{
			glEnableVertexAttribArray(i);
		}
	}

	public void disableAttrib(int index)
	{
		glDisableVertexAttribArray(index);
	}

	public void disableAttribs()
	{
		for(int i = 0; i < attribs; i++)
		{
			glDisableVertexAttribArray(i);
		}
	}

	public int vertexSize()
	{
		return positionSize + uvSize;
	}

	public int vertexSizeByte()
	{
		return (positionSize + uvSize) * Float.BYTES;
	}

	public byte getPositionSize()
	{
		return positionSize;
	}

	public byte getUvSize()
	{
		return uvSize;
	}

	public void destroy()
	{
		glDeleteVertexArrays(vao);
	}
}
