package game.jgengine.graphics.shapes;

import game.jgengine.entity.GameObject;
import game.jgengine.graphics.rendering.Sprite;
import game.jgengine.graphics.vertex.Mesh;
import game.jgengine.graphics.rendering.Texture;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector2f;

public class Rectangle extends GameObject
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
		super(new Mesh(
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
