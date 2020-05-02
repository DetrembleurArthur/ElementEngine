package game.jgengine.graphics.vertex;

import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.system.MemoryUtil.memFree;

public class IndexBuffer
{
	private int ibo = 0;
	private int len = 0;

	public IndexBuffer(int[] indexes)
	{
		initIbo();
		initIndexes(indexes);
		len = indexes.length;
	}

	private static IntBuffer initBuffer(int[] indexes)
	{
		IntBuffer buffer = MemoryUtil.memAllocInt(indexes.length);
		buffer.put(indexes).flip();
		return buffer;
	}

	private void initIbo()
	{
		ibo = glGenBuffers();
	}

	private void initIndexes(int[] indexes)
	{
		IntBuffer buffer = initBuffer(indexes);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		if(buffer != null)
			memFree(buffer);
	}

	public void update(int[] indexes)
	{
		update(initBuffer(indexes));
	}

	public void update(IntBuffer buffer)
	{
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, 0, buffer);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		if(buffer != null)
			memFree(buffer);
	}

	public void update(int offset, int[] buffer)
	{
		update(offset, initBuffer(buffer));
	}

	public void update(int offset, IntBuffer buffer)
	{
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, offset, buffer);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		if(buffer != null)
			memFree(buffer);
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
		return len;
	}

	public void destroy()
	{
		glDeleteBuffers(ibo);
	}

	public void drawElements(int drawType)
	{
		bind();
		glDrawElements(drawType,len, GL_UNSIGNED_INT, 0);
		unbind();
	}
}
