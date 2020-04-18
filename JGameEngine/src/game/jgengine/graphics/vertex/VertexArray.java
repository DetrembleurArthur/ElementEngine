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
		int floatSize = 4;
		int vertexSize = (positionSize + colorSize) * floatSize;
		glVertexAttribPointer(0, positionSize, GL_FLOAT, true, vertexSize, 0);
		glEnableVertexAttribArray(0);
		if(colorSize != 0)
		{
			glVertexAttribPointer(1, colorSize, GL_FLOAT, true, vertexSize, positionSize * floatSize);
			glEnableVertexAttribArray(1);
			attribs = 2;
		}
		else
			attribs = 1;
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
		if(attribs == 2)
		{
			glEnableVertexAttribArray(1);
		}
	}

	public void disableAttrib(int index)
	{
		glDisableVertexAttribArray(index);
	}

	public void disableAttribs()
	{
		glDisableVertexAttribArray(0);
		if(attribs == 2)
		{
			glDisableVertexAttribArray(1);
		}
	}

	public void destroy()
	{
		glDeleteBuffers(vao);
	}
}
