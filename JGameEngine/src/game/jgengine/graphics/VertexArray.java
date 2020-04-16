package game.jgengine.graphics;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class VertexArray
{
	private int vao = 0;

	public VertexArray()
	{
		vao = glGenVertexArrays();
		bind();
	}

	public void initAttribs()
	{
		int positionSize = 3;
		int colorSize = 4;
		int floatSize = 4;
		int vertexSize = (positionSize + colorSize) * floatSize;
		glVertexAttribPointer(0, positionSize, GL_FLOAT, true, vertexSize, 0);
		glEnableVertexAttribArray(0);
		glVertexAttribPointer(1, colorSize, GL_FLOAT, true, vertexSize, positionSize * floatSize);
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

	public void disableAttrib(int index)
	{
		glDisableVertexAttribArray(index);
	}

	public void destroy()
	{
		glDeleteBuffers(vao);
	}
}
