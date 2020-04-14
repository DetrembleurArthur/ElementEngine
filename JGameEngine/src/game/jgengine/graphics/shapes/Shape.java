package game.jgengine.graphics.shapes;

import game.jgengine.graphics.Drawable;
import game.jgengine.graphics.Transformable;
import game.jgengine.graphics.VertexArray;

public abstract class Shape extends Transformable implements Drawable
{
	protected VertexArray verteces = null;

	protected abstract void updateVerteces();

	public VertexArray getVerteces()
	{
		return verteces;
	}

	public void setVerteces(VertexArray verteces)
	{
		this.verteces = verteces;
	}
}
