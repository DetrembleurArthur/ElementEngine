package game.jgengine.graphics.shapes;

import game.jgengine.entity.GraphicElement;
import game.jgengine.graphics.rendering.Texture;
import game.jgengine.graphics.vertex.Mesh;
import org.joml.Vector2f;

public class Line extends GraphicElement
{
	public Line(Vector2f p1, Vector2f p2)
	{
		super(Line.build(p1, p2));
		setLinesRenderMode();
	}

	public static Mesh build(Vector2f p1, Vector2f p2)
	{
		return new Mesh(new float[]{ p1.x, p1.y, p2.x, p2.y }, new int[]{ 0, 1 }, Mesh.DIMENSION_2, Mesh.NO_TEXTURED);
	}

	public void setBeginPoint(Vector2f point)
	{
		getMesh().setPosition(0, point);
	}

	public void setEndPoint(Vector2f point)
	{
		getMesh().setPosition(1, point);
	}

	public Vector2f getBeginPoint()
	{
		return getMesh().getPosition(0);
	}

	public Vector2f getEndPoint()
	{
		return getMesh().getPosition(1);
	}
}
