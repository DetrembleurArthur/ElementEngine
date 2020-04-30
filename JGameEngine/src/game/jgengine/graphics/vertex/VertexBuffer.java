package game.jgengine.graphics.vertex;

import game.jgengine.utils.Color;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL15.*;

public class VertexBuffer
{
	private int vbo = 0;
	private float[] buffer;
	//private float[] verticesArray;

	public VertexBuffer(float[] vertices)
	{

		initBuffer(vertices);
		initVbo();
		//verticesArray = vertices;
	}

	public float[] getArray()
	{
		return buffer;
	}

	private void initBuffer(float[] vertices)
	{
		buffer = vertices;
		//buffer.put(vertices).flip();
	}

	private void initVbo()
	{
		vbo = glGenBuffers();
		majorUpdate();
	}

	public void bind()
	{
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
	}

	public void unbind()
	{
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public void majorUpdate()
	{
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public void update(float[] vertices)
	{
		buffer = vertices;
		update(0, buffer);
	}


	public void update(int offset, float[] buffer)
	{
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferSubData(GL_ARRAY_BUFFER, offset, buffer);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public void translate(Vector3f transform)
	{
		int len = buffer.length / 7;
		for(int i = 0; i < len; i++)
		{
			buffer[i * 7] += transform.x;
			buffer[i * 7 + 1] += transform.y;
			buffer[i * 7 + 2] += transform.z;
		}
		update(0, buffer);
	}

	public void translate(Vector2f transform)
	{
		int len = buffer.length / 7;
		for(int i = 0; i < len; i++)
		{
			buffer[i * 7] += transform.x;
			buffer[i * 7 + 1] += transform.y;
		}
		update(0, buffer);
	}


	public void setVertexPosition(int index, float x, float y, float z)
	{
		buffer[index * 7 ] = x;
		buffer[index * 7 + 1] = y;
		buffer[index * 7 + 2] = z;
		update(index * 7 * 4, new float[]{x, y, z});
	}

	public Vector2f getVertexPosition(int index)
	{
		float[] buffer = new float[2];
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glGetBufferSubData(GL_ARRAY_BUFFER, index * 7 * 4, buffer);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		return new Vector2f(buffer[0], buffer[1]);
	}

	public void setVertexColor(int index, float r, float g, float b, float a)
	{
		buffer[index * 7 + 3] = r;
		buffer[index * 7 + 4] = g;
		buffer[index * 7 + 5] = b;
		buffer[index * 7 + 6] = a;
		update((index * 7 + 3) * 4, new float[]{r, g, b, a});
	}


	public Color getVertexColor(int index)
	{
		float[] buffer = new float[4];
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glGetBufferSubData(GL_ARRAY_BUFFER, (index * 7 + 3) * 4, buffer);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		return new Color(buffer[0], buffer[1], buffer[2], buffer[3]);
	}

	public void addVertex(Vector2f position, Color color)
	{
		float[] newBuffer = new float[buffer.length + 7];
		for(int i = 0; i < buffer.length; i++)
		{
			newBuffer[i] = buffer[i];
		}
		newBuffer[buffer.length] = position.x;
		newBuffer[buffer.length + 1] = position.y;
		newBuffer[buffer.length + 2] = 0f;
		newBuffer[buffer.length + 3] = color.getRedRatio();
		newBuffer[buffer.length + 4] = color.getGreenRatio();
		newBuffer[buffer.length + 5] = color.getBlueRatio();
		newBuffer[buffer.length + 6] = color.getAlphaRatio();
		buffer=newBuffer;
		majorUpdate();
	}

	public void subVertex()
	{
		if(buffer.length > 0)
		{
			float[] newBuffer = new float[buffer.length - 7];
			for(int i = 0; i < buffer.length - 7; i++)
			{
				newBuffer[i] = buffer[i];
			}
			buffer = newBuffer;
			majorUpdate();
		}
	}

	public void destroy()
	{
		glDeleteBuffers(vbo);
	}
}