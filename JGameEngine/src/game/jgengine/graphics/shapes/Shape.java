package game.jgengine.graphics.shapes;

import game.jgengine.graphics.vertex.GraphicElement;

public class Shape extends GraphicElement
{
	protected Shape(int drawType)
	{
		super(drawType);
	}

	public Shape(float[] vertices, int[] indexes, int drawType)
	{
		super(vertices, indexes, drawType);
	}
}
