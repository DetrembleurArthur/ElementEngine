package game.jgengine.graphics.shapes;

import game.jgengine.debug.Logs;
import game.jgengine.entity.GameObject;
import game.jgengine.graphics.rendering.Sprite;
import game.jgengine.graphics.vertex.Mesh;
import game.jgengine.graphics.rendering.Texture;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;

public class Rectangle extends GameObject
{
	public static final Mesh MODEL = new Mesh(
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
	private static boolean commonModel = false;

	public static boolean isCommonModel()
	{
		return commonModel;
	}

	public static void setCommonModel(boolean commonModel)
	{
		Rectangle.commonModel = commonModel;
	}

	public Rectangle(Texture texture)
	{
		super(commonModel ? MODEL : new Mesh(
				new float[]{
						0, 0,	0, 0, //top left
						0, 1,	0, 1, //bottom left
						1, 1,	1, 1, //bottom right
						1, 0,	1, 0 //top right
				},
				new int[]{
						0, 1, 2, 0, 2, 3
				},Mesh.DIMENSION_2, Mesh.TEXTURED
		), texture);
		if(texture != null)
			setSize(texture.getDimension());
	}

	public Rectangle()
	{
		this(null);
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

	public ArrayList<Triangle> getFragments(int nwidth, int nheight)
	{
		ArrayList<Triangle> fragments = new ArrayList<>();
		var size = getSize2D().div(new Vector2f(nwidth, nheight));
		for(int i = 0; i < nwidth; i++)
		{
			for(int j = 0; j < nheight; j++)
			{
				Triangle fragment = new Triangle(getTexture());
				fragment.setPoints(
						new Vector2f(0, 0),
						new Vector2f(0, 1),
						new Vector2f(1, 1));
				fragment.getMesh().setUV(0, new Vector2f(i * (1f / nwidth), j * (1f / nheight)));
				fragment.getMesh().setUV(1, new Vector2f(i * (1f / nwidth), j * (1f / nheight) + (1f / nheight)));
				fragment.getMesh().setUV(2, new Vector2f(i * (1f / nwidth) + (1f / nwidth), j * (1f / nheight) + (1f / nheight)));
				fragment.setSize(size);
				fragment.setCenterOrigin();
				fragment.setTopLeftPosition(new Vector2f(getTopLeftPosition().x + i * size.x, getTopLeftPosition().y + j * size.y));
				fragments.add(fragment);
				fragment = new Triangle(getTexture());
				fragment.setPoints(
						new Vector2f(0, 0),
						new Vector2f(1, 1),
						new Vector2f(1, 0));
				fragment.getMesh().setUV(0, new Vector2f(i * (1f / nwidth), j * (1f / nheight)));
				fragment.getMesh().setUV(1, new Vector2f(i * (1f / nwidth) + (1f / nwidth), j * (1f / nheight) + (1f / nheight)));
				fragment.getMesh().setUV(2, new Vector2f(i * (1f / nwidth) + (1f / nwidth), j * (1f / nheight)));
				fragment.setSize(size);
				fragment.setCenterOrigin();
				fragment.setTopLeftPosition(new Vector2f(getTopLeftPosition().x + i * size.x, getTopLeftPosition().y + j * size.y));
				fragments.add(fragment);
			}
		}
		return fragments;
	}

	@Override
	public void destroy()
	{
		if(getMesh() != MODEL)
			super.destroy();
	}
}
