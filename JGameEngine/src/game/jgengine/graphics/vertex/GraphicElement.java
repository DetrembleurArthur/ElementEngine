package game.jgengine.graphics.vertex;

import game.jgengine.graphics.shaders.Shader;
import game.jgengine.utils.Color;
import game.jgengine.utils.Vec2f;

public class GraphicElement
{
	protected VertexArray vertexArray;
	protected VertexBuffer vertexBuffer;
	protected IndexBuffer indexBuffer;
	private int drawType;

	protected GraphicElement(int drawType)
	{
		this.drawType = drawType;
	}

	public int getDrawType()
	{
		return drawType;
	}

	public void setDrawType(int drawType)
	{
		this.drawType = drawType;
	}

	public GraphicElement(float[] vertices, int[] indexes, int drawType)
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
		vertexArray.initAttribs(3, 4);
		vertexBuffer.unbind();
		indexBuffer.unbind();
	}


	public void setVertices(float[] vertices)
	{
		vertexBuffer.update(vertices);
	}

	public void setIndexes(int[] indexes)
	{
		indexBuffer.update(indexes);
	}


	public VertexArray getVertexArray()
	{
		return vertexArray;
	}
	public VertexBuffer getVertexBuffer()
	{
		return vertexBuffer;
	}
	public IndexBuffer getIndexBuffer()
	{
		return indexBuffer;
	}

	public void addVertex(Vec2f position, Color color)
	{
		vertexBuffer.addVertex(position, color);
		indexBuffer.addIndex(indexBuffer.getLen());
		vertexBuffer.bind();
		indexBuffer.bind();
		vertexArray.initAttribs(3, 4);
		vertexBuffer.unbind();
		indexBuffer.unbind();
	}

	public void subVertex()
	{
		vertexBuffer.subVertex();
		indexBuffer.subIndex();
		vertexBuffer.bind();
		indexBuffer.bind();
		vertexArray.initAttribs(3, 4);
		vertexBuffer.unbind();
		indexBuffer.unbind();
	}

	public void draw()
	{
		vertexArray.bind();

		vertexArray.enableAttribs();
		indexBuffer.drawElements(drawType);

		vertexArray.disableAttribs();


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
