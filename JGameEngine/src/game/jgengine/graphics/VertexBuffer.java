package game.jgengine.graphics;

import game.jgengine.graphics.Vertex;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;

public class VertexBuffer
{
	private int vbo = 0;
	private FloatBuffer buffer;
	private float[] verticesArray;

	public VertexBuffer(float[] vertices)
	{

		initBuffer(vertices);
		initVbo();
		verticesArray = vertices;
	}

	public float[] getArray()
	{
		return verticesArray;
	}

	private void initBuffer(float[] vertices)
	{
		buffer = BufferUtils.createFloatBuffer(vertices.length);
		buffer.put(vertices).flip();
	}

	private void initVbo()
	{
		vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_DYNAMIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public void bind()
	{
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
	}

	public void unbind()
	{
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public void update(float[] vertices)
	{
		buffer.clear().put(vertices).flip();
		verticesArray = vertices;
		update();
	}


	public void update()
	{
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferSubData(GL_ARRAY_BUFFER, 0, buffer);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public void setVertexPosition(int index, float x, float y, float z)
	{
		float[] pos = new float[]{x, y, z};
		buffer.put(index * 7, pos);
		update();
	}

	public void setVertexColor(int index, float r, float g, float b, float a)
	{
		float[] c = new float[]{r, g, b, a};
		buffer.put(index * 7 + 3, c);
		update();
	}

	public void setVertex(int index, Vertex vertex)
	{
		buffer.put(index * 7, new float[]{vertex.x, vertex.y, vertex.z, vertex.r, vertex.g, vertex.b, vertex.a});
		update();
	}

	public void destroy()
	{
		glDeleteBuffers(vbo);
	}
}
