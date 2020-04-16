package game.jgengine.graphics;

import org.lwjgl.BufferUtils;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;

public class IndexBuffer
{
	private int ibo = 0;
	private IntBuffer buffer;
	private int[] indexArray;

	public IndexBuffer(int[] indexes)
	{
		initBuffer(indexes);
		initIbo();
		indexArray = indexes;
	}

	public int[] getArray()
	{
		return indexArray;
	}

	private void initBuffer(int[] indexes)
	{
		buffer = BufferUtils.createIntBuffer(indexes.length);
		buffer.put(indexes).flip();
	}

	private void initIbo()
	{
		ibo = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_DYNAMIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}

	public void update(int[] indexes)
	{
		buffer.clear().put(indexes).flip();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, 0, buffer);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		indexArray = indexes;
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
		return indexArray.length;
	}

	public void destroy()
	{
		glDeleteBuffers(ibo);
	}

	public void drawElements(int drawType)
	{
		bind();
		glDrawElements(drawType, indexArray.length, GL_UNSIGNED_INT, 0);
		unbind();
	}
}
