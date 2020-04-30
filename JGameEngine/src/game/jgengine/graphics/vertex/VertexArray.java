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

	public void initAttribs(int dimension, int colorMode)
	{
		int positionSize = dimension;
		int colorSize = colorMode;
		int uvSize = 2;
		int floatSize = Float.BYTES;
		int vertexSize = (positionSize + colorSize + uvSize) * floatSize;
		glVertexAttribPointer(0, positionSize, GL_FLOAT, false, vertexSize, 0);
		glEnableVertexAttribArray(0);
		glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSize, positionSize * floatSize);
		glEnableVertexAttribArray(1);
		glVertexAttribPointer(2, uvSize, GL_FLOAT, false, vertexSize, (positionSize + colorSize) * floatSize);
		glEnableVertexAttribArray(2);

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
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
	}

	public void disableAttrib(int index)
	{
		glDisableVertexAttribArray(index);
	}

	public void disableAttribs()
	{
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(2);
	}

	public void destroy()
	{
		glDeleteBuffers(vao);
	}
}
