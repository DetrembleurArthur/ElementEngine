package game.jgengine.graphics;

public class VertexArray implements Drawable
{
	private Vertex[] verteces;

	public VertexArray(Vertex[] verteces)
	{
		this.verteces = verteces;
	}

	public VertexArray(int size)
	{
		verteces = new Vertex[size];
	}

	public void set(int i, Vertex vertex)
	{
		verteces[i] = vertex;
	}

	@Override
	public void draw()
	{
		for(Vertex vertex : verteces)
		{
			vertex.draw();
		}
	}
}
