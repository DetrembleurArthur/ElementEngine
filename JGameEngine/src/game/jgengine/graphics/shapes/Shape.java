package game.jgengine.graphics.shapes;

import game.jgengine.graphics.IndexBuffer;
import game.jgengine.graphics.VertexArray;
import game.jgengine.graphics.shaders.Shader;
import game.jgengine.graphics.VertexBuffer;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;

public class Shape
{
	protected VertexArray vertexArray;
	protected VertexBuffer vertexBuffer;
	protected IndexBuffer indexBuffer;
	protected int drawType = GL_TRIANGLES;

	protected Shape(int drawType)
	{
		this.drawType = drawType;
	}

	public Shape(float[] vertices, int[] indexes, int drawType)
	{
		initVertices(vertices, indexes);
		this.drawType = drawType;
	}

	protected void initVertices(float[] vertices, int[] indexes)
	{
		vertexArray = new VertexArray();
		vertexBuffer = new VertexBuffer(vertices);
		indexBuffer = new IndexBuffer(indexes);
		vertexBuffer.bind();
		indexBuffer.bind();
		vertexArray.initAttribs();
		vertexBuffer.unbind();
		indexBuffer.unbind();
	}

	protected void reloadVertices()
	{
		vertexArray.destroy();
		vertexArray = new VertexArray();
		vertexBuffer.bind();
		indexBuffer.bind();
		vertexArray.initAttribs();
		vertexBuffer.unbind();
		indexBuffer.unbind();
	}

	protected void reloadVertices(float[] vertices, int[] indexes)
	{
		vertexBuffer.destroy();
		indexBuffer.destroy();
		vertexArray.destroy();
		initVertices(vertices, indexes);
	}

	public void setVertices(float[] vertices)
	{
		vertexBuffer.update(vertices);
	}

	public void setIndexes(int[] indexes)
	{
		indexBuffer.update(indexes);
	}


	public VertexBuffer getVertices()
	{
		return vertexBuffer;
	}

	public void draw()
	{
		vertexArray.bind();

		vertexArray.enableAttrib(0);
		vertexArray.enableAttrib(1);

		indexBuffer.drawElements(drawType);

		vertexArray.disableAttrib(0);
		vertexArray.disableAttrib(1);


		vertexArray.unbind();
	}

	public void draw(Shader shader)
	{
		shader.start();
		draw();
		shader.stop();
	}

	public void destroy()
	{
		vertexArray.destroy();
		vertexBuffer.destroy();
		indexBuffer.destroy();
	}
}
