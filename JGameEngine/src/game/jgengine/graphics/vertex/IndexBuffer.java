package game.jgengine.graphics.vertex;

import org.lwjgl.BufferUtils;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;

public class IndexBuffer
{
	private int ibo = 0;
	private int[] buffer;

	public IndexBuffer(int[] indexes)
	{
		initBuffer(indexes);
		initIbo();
	}

	public int[] getArray()
	{
		return buffer;
	}

	private void initBuffer(int[] indexes)
	{
		buffer = indexes;
	}

	private void initIbo()
	{
		ibo = glGenBuffers();
		majorUpdate();
	}

	public void update(int[] indexes)
	{
		buffer = indexes;
		update(0, buffer);
	}

	public void majorUpdate()
	{
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}

	public void update(int offset, int[] buffer)
	{
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, offset, buffer);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}

	public void addIndex(int element)
	{
		int[] newBuffer = new int[buffer.length + 1];
		for(int i = 0; i < buffer.length; i++)
		{
			newBuffer[i] = buffer[i];
		}
		newBuffer[buffer.length] = element;
		buffer = newBuffer;
		majorUpdate();
	}

	public void subIndex()
	{
		if(buffer.length > 0)
		{
			int[] newBuffer = new int[buffer.length - 1];
			for (int i = 0; i < buffer.length - 1; i++)
			{
				newBuffer[i] = buffer[i];
			}
			buffer = newBuffer;
			majorUpdate();
		}
	}

	public void bind()
	{
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
	}

	public void unbind()
	{
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}

	public int getLen()
	{
		return buffer.length;
	}

	public void destroy()
	{
		glDeleteBuffers(ibo);
	}

	public void drawElements(int drawType)
	{
		bind();
		glDrawElements(drawType, buffer.length, GL_UNSIGNED_INT, 0);
		unbind();
	}
}
