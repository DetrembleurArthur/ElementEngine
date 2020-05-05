package game.jgengine.graphics.shapes;

import game.jgengine.graphics.GameObject;
import game.jgengine.graphics.Mesh;
import game.jgengine.graphics.shaders.Texture;
import org.w3c.dom.Text;

public class Rectangle extends GameObject
{
	private static final Mesh TEXTURED_MESH = new Mesh(new float[]{
			-1f, 1f,        0,0,
			-1f, -1f,     0,1,
			1f, 1f,       1,0,
			1f, -1f,  1,1
	}, new int[]{
			0, 1,2,
			3,2,1
	}, Mesh.DIMENSION_2, Mesh.NO_COLOR, Mesh.TEXTURED);

	private static final Mesh NO_TEXTURED_MESH = new Mesh(new float[]{
			-1f, 1f,
			-1f, -1f,
			1f, 1f,
			1f, -1f,
	}, new int[]{
			0, 1,2,
			3,2,1
	}, Mesh.DIMENSION_2, Mesh.NO_COLOR, Mesh.NO_TEXTURED);

	public Rectangle()
	{
		this(null);
	}

	public Rectangle(int colorMode, Texture texture)
	{
		super(colorMode == 3 && (texture != null) ? new Mesh(new float[]{
				-1f, 1f,      1f,1f,1f,		0,0,
				-1f, -1f,      1f,1f,1f,	0, 1,
				1f, 1f,     1f,1f,1f,		1,0,
				1f, -1f,     1f,1f,1f,		1,1
		}, new int[]{
				0, 1,2,
				3,2,1
		}, Mesh.DIMENSION_2, Mesh.RGB, Mesh.TEXTURED) : (colorMode == 3 && !(texture != null) ?
			new Mesh(new float[]{
					-1f, 1f,      1f,1f,1f,
					-1f, -1f,      1f,1f,1f,
					1f, 1f,     1f,1f,1f,
					1f, -1f,     1f,1f,1f,
			}, new int[]{
					0, 1,2,
					3,2,1
			}, Mesh.DIMENSION_2, Mesh.RGB, Mesh.NO_TEXTURED) : (colorMode == 4 && (texture != null) ?
			new Mesh(new float[]{
					-1f, 1f,      1f,1f,1f,1f,		0,0,
					-1f, -1f,      1f,1f,1f,1f,	0, 1,
					1f, 1f,     1f,1f,1f,1f,		1,0,
					1f, -1f,     1f,1f,1f,1f,		1,1
			}, new int[]{
					0, 1,2,
					3,2,1
			}, Mesh.DIMENSION_2, Mesh.RGBA, Mesh.TEXTURED) :
				new Mesh(new float[]{
					-1f, 1f,      1f,1f,1f,1f,
					-1f, -1f,      1f,1f,1f,1f,
					1f, 1f,     1f,1f,1f,1f,
					1f, -1f,     1f,1f,1f,1f
					}, new int[]{
							0, 1,2,
					3,2,1
					}, Mesh.DIMENSION_2, Mesh.RGBA, Mesh.NO_TEXTURED))), texture);
	}

	public Rectangle(int colorMode)
	{
		this(colorMode, null);
	}

	public Rectangle(Texture texture)
	{
		super(texture == null ? NO_TEXTURED_MESH : TEXTURED_MESH, texture);
	}
}
