package game.jgengine.graphics;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Vertices
{
	private int vao;
	private int vbo;
	private int ebo;
	private int ni;

	public Vertices(float[] vertices, int[] indexes)
	{
		vao = glGenVertexArrays();
		glBindVertexArray(vao);

		FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices.length);
		vertexBuffer.put(vertices).flip();

		vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

		IntBuffer indexesBuffer = BufferUtils.createIntBuffer(indexes.length);
		indexesBuffer.put(indexes).flip();

		if(indexes != null)
		{
			ebo = glGenBuffers();
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
			glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexesBuffer, GL_STATIC_DRAW);
			ni = indexes.length;
		}
		int positionSize = 3;
		int colorSize = 4;
		int floatSize = 4;
		int vertexSize = (positionSize + colorSize) * floatSize;
		glVertexAttribPointer(0, positionSize, GL_FLOAT, true, vertexSize, 0);
		glEnableVertexAttribArray(0);
		glVertexAttribPointer(1, colorSize, GL_FLOAT, true, vertexSize, positionSize * floatSize);
		glEnableVertexAttribArray(1);
	}

	public void draw(int drawMode)
	{
		glBindVertexArray(vao);

		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);

		glDrawElements(drawMode, ni, GL_UNSIGNED_INT, 0);

		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);

		glBindVertexArray(0);
	}
}
