package game.jgengine.graphics.shapes;

import game.jgengine.graphics.vertex.Mesh;
import game.jgengine.graphics.rendering.Texture;
import game.jgengine.utils.MathUtil;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector2f;

public class Rectangle extends Shape
{
	private static final Mesh MODEL = new Mesh(
			new float[]{
					0, 0,	0, 0, //top left
					0, 1,	0, 1, //bottom left
					1, 1,	1, 1, //bottom right
					1, 0,	1, 0 //top right
			},
			new int[]{
					0, 1, 2, 0, 2, 3
			},Mesh.DIMENSION_2, Mesh.TEXTURED
	);

	public Rectangle(Texture texture)
	{
		super(MODEL, texture);
		if(texture != null)
			setDimension(texture.getDimension());
	}

	public void setWidth(float width)
	{
		getScale().x = width;
	}

	public void setHeight(float height)
	{
		getScale().y = height;
	}

	public void setDimension(Vector2f dimension)
	{
		getScale().x = dimension.x;
		getScale().y = dimension.y;
	}

	public void setDimension(float x, float y)
	{
		getScale().x = x;
		getScale().y = y;
	}

	public Vector2f getDimension()
	{
		var scale = getScale();
		return new Vector2f(scale.x, scale.y);
	}


	@Override
	protected void setVerticesOrigin(float x, float y)
	{
		Mesh mesh = getMesh();
		for(int i = 0; i < MODEL.getN(); i++)
		{
			Vector2f pos = new Vector2f(MODEL.getPosition(i)).add(x, y);
			mesh.setPosition(i, pos);
		}
	}

	//marche que pour le top left origin
	public boolean contains(Vector2f pos)
	{
		//on applique la rotation du gameobject sur le point pos et on check la collision
		return MathUtil.boxContains(getPosition2D(), getDimension(), MathUtil.rotateAround(pos, getPosition2D(), -getRotation2D()));
	}

	public void setSprite(@NotNull Sprite sprite)
	{
		Mesh mesh = getMesh();
		setTexture(sprite.getTexture());
		Vector2f[] textCoords = sprite.getTextCoords();
		mesh.setUV(0, textCoords[0]);
		mesh.setUV(1, textCoords[1]);
		mesh.setUV(2, textCoords[2]);
		mesh.setUV(3, textCoords[3]);
	}
}
