package game.jgengine.graphics.vertex;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class VertexArray
{
	private int vao = 0;
	private byte attribs = 0;

	public VertexArray()
	{
		vao = glGenVertexArrays();
		bind();
	}


	public void initAttribs(int dimension, int uv)
	{
		if(dimension != 2 && dimension != 3)
			dimension = 3;

		int positionSize = dimension;
		int uvSize = uv;
		int vertexSize = (positionSize + uv) * Float.BYTES;
		attribs = 1;
		glVertexAttribPointer(0, positionSize, GL_FLOAT, false, vertexSize, 0);
		glEnableVertexAttribArray(0);
		if(uv > 0)
		{
			attribs++;
			glVertexAttribPointer(1, uvSize, GL_FLOAT, false, vertexSize, positionSize * Float.BYTES);
			glEnableVertexAttribArray(1);
		}
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

	public void destroy()
	{
		glDeleteBuffers(vao);
	}
}
