package game.jgengine.graphics.shapes;

import game.jgengine.graphics.GameObject;
import game.jgengine.graphics.Mesh;
import game.jgengine.graphics.shaders.Texture;
import org.w3c.dom.Text;

public class Cube extends GameObject
{
	private static final Mesh TEXTURED_MESH = new Mesh(new float[]{
			//Front
			-1f, 1f, -1f,    0,0,
			-1f, -1f,-1f,      0,1,
			1f, 1f,-1f,           1,0,
			1f, -1f,-1f,      1,1,
			//Back
			-1f, 1f, 1f,       0,0,
			-1f, -1f,1f,     0,1,
			1f, 1f,1f,          1,0,
			1f, -1f,1f,      1,1,
			//Up
			-1f, 1f, 1f,      0,0,
			-1f, 1f,-1f,     0,1,
			1f, 1f,1f,     1,0,
			1f, 1f,-1f,      1,1,
			//Down
			-1f,- 1f, 1f,  0,0,
			-1f, -1f,-1f,     0,1,
			1f, -1f,1f,        1,0,
			1f, -1f,-1f,       1,1,
			//Left
			-1f, 1f, 1f,       0,0,
			-1f, -1f,1f,       0,1,
			-1f, 1f,-1f,       1,0,
			-1f, -1f,-1f,        1,1,
			//Right
			1f, 1f, 1f,   0,0,
			1f, -1f,1f,  0,1,
			1f, 1f,-1f,          1,0,
			1f, -1f,-1f,      1,1
	}, new int[]{
			0, 1,2,
			3,2,1,

			4, 5,6,
			7,6,5,

			8, 9,10,
			11,10,9,

			12, 13,14,
			15,14,13,

			16, 17,18,
			19,18,17,

			20, 21,22,
			23,22,21
	}, Mesh.DIMENSION_3, Mesh.NO_COLOR, Mesh.TEXTURED);

	private static final Mesh NO_TEXTURED_MESH = new Mesh(new float[]{
			//Front
			-1f, 1f, -1f,
			-1f, -1f,-1f,
			1f, 1f,-1f,
			1f, -1f,-1f,
			//Back
			-1f, 1f, 1f,
			-1f, -1f,1f,
			1f, 1f,1f,
			1f, -1f,1f,
			//Up
			-1f, 1f, 1f,
			-1f, 1f,-1f,
			1f, 1f,1f,
			1f, 1f,-1f,
			//Down
			-1f,- 1f, 1f,
			-1f, -1f,-1f,
			1f, -1f,1f,
			1f, -1f,-1f,
			//Left
			-1f, 1f, 1f,
			-1f, -1f,1f,
			-1f, 1f,-1f,
			-1f, -1f,-1f,
			//Right
			1f, 1f, 1f,
			1f, -1f,1f,
			1f, 1f,-1f,
			1f, -1f,-1f,
	}, new int[]{
			0, 1,2,
			3,2,1,

			4, 5,6,
			7,6,5,

			8, 9,10,
			11,10,9,

			12, 13,14,
			15,14,13,

			16, 17,18,
			19,18,17,

			20, 21,22,
			23,22,21
	}, Mesh.DIMENSION_3, Mesh.NO_COLOR, Mesh.NO_TEXTURED);

	public Cube(Texture texture)
	{
		super(TEXTURED_MESH, texture);
	}

	public Cube()
	{
		super(NO_TEXTURED_MESH, null);
	}

	public Cube(int colorMode, Texture texture)
	{
		super(colorMode == 3 && (texture != null) ? new Mesh(new float[]{
				//Front
				-1f, 1f, -1f,   1f,1f,1f, 	0,0,
				-1f, -1f,-1f,   1f,1f,1f,     	0,1,
				1f, 1f,-1f,      1f,1f,1f,     1,0,
				1f, -1f,-1f,    1f,1f,1f,    	1,1,
				//Back
				-1f, 1f, 1f,    1f,1f,1f,     0,0,
				-1f, -1f,1f,    1f,1f,1f,   0,1,
				1f, 1f,1f,     1f,1f,1f,       1,0,
				1f, -1f,1f,     1f,1f,1f,   1,1,
				//Up
				-1f, 1f, 1f,     1f,1f,1f,   0,0,
				-1f, 1f,-1f,    1f,1f,1f,   0,1,
				1f, 1f,1f,      1f,1f,1f, 1,0,
				1f, 1f,-1f,      1f,1f,1f,  1,1,
				//Down
				-1f,- 1f, 1f,   1f,1f,1f, 0,0,
				-1f, -1f,-1f,     1f,1f,1f,  0,1,
				1f, -1f,1f,       1f,1f,1f,   1,0,
				1f, -1f,-1f,      1f,1f,1f,   1,1,
				//Left
				-1f, 1f, 1f,      1f,1f,1f,   0,0,
				-1f, -1f,1f,      1f,1f,1f,   0,1,
				-1f, 1f,-1f,      1f,1f,1f,   1,0,
				-1f, -1f,-1f,     1f,1f,1f,     1,1,
				//Right
				1f, 1f, 1f,    1f,1f,1f, 0,0,
				1f, -1f,1f,   1f,1f,1f, 0,1,
				1f, 1f,-1f,    1f,1f,1f,        1,0,
				1f, -1f,-1f,    1f,1f,1f,    1,1
		}, new int[]{
				0, 1,2,
				3,2,1,

				4, 5,6,
				7,6,5,

				8, 9,10,
				11,10,9,

				12, 13,14,
				15,14,13,

				16, 17,18,
				19,18,17,

				20, 21,22,
				23,22,21
		}, Mesh.DIMENSION_3, Mesh.RGB, Mesh.TEXTURED) : (colorMode == 3 && !(texture != null) ?
				new Mesh(new float[]{
						//Front
						-1f, 1f, -1f,   1f,1f,1f,
						-1f, -1f,-1f,   1f,1f,1f,
						1f, 1f,-1f,      1f,1f,1f,
						1f, -1f,-1f,    1f,1f,1f,
						//Back
						-1f, 1f, 1f,    1f,1f,1f,
						-1f, -1f,1f,    1f,1f,1f,
						1f, 1f,1f,     1f,1f,1f,
						1f, -1f,1f,     1f,1f,1f,
						//Up
						-1f, 1f, 1f,     1f,1f,1f,
						-1f, 1f,-1f,    1f,1f,1f,
						1f, 1f,1f,      1f,1f,1f,
						1f, 1f,-1f,      1f,1f,1f,
						//Down
						-1f,- 1f, 1f,   1f,1f,1f,
						-1f, -1f,-1f,     1f,1f,1f,
						1f, -1f,1f,       1f,1f,1f,
						1f, -1f,-1f,      1f,1f,1f,
						//Left
						-1f, 1f, 1f,      1f,1f,1f,
						-1f, -1f,1f,      1f,1f,1f,
						-1f, 1f,-1f,      1f,1f,1f,
						-1f, -1f,-1f,     1f,1f,1f,
						//Right
						1f, 1f, 1f,    1f,1f,1f,
						1f, -1f,1f,   1f,1f,1f,
						1f, 1f,-1f,    1f,1f,1f,
						1f, -1f,-1f,    1f,1f,1f,
				}, new int[]{
						0, 1,2,
						3,2,1,

						4, 5,6,
						7,6,5,

						8, 9,10,
						11,10,9,

						12, 13,14,
						15,14,13,

						16, 17,18,
						19,18,17,

						20, 21,22,
						23,22,21
				}, Mesh.DIMENSION_3, Mesh.RGB, Mesh.NO_TEXTURED) : (colorMode == 4 && (texture != null) ?
				new Mesh(new float[]{
						//Front
						-1f, 1f, -1f,   1f,1f,1f,1f, 	0,0,
						-1f, -1f,-1f,   1f,1f,1f,1f,     	0,1,
						1f, 1f,-1f,      1f,1f,1f,1f,     1,0,
						1f, -1f,-1f,    1f,1f,1f,1f,    	1,1,
						//Back
						-1f, 1f, 1f,    1f,1f,1f,1f,     0,0,
						-1f, -1f,1f,    1f,1f,1f,1f,   0,1,
						1f, 1f,1f,     1f,1f,1f,1f,       1,0,
						1f, -1f,1f,     1f,1f,1f,1f,   1,1,
						//Up
						-1f, 1f, 1f,     1f,1f,1f,1f,   0,0,
						-1f, 1f,-1f,    1f,1f,1f,1f,   0,1,
						1f, 1f,1f,      1f,1f,1f,1f, 1,0,
						1f, 1f,-1f,      1f,1f,1f,1f,  1,1,
						//Down
						-1f,- 1f, 1f,   1f,1f,1f,1f, 0,0,
						-1f, -1f,-1f,     1f,1f,1f,1f,  0,1,
						1f, -1f,1f,       1f,1f,1f,1f,   1,0,
						1f, -1f,-1f,      1f,1f,1f,1f,   1,1,
						//Left
						-1f, 1f, 1f,      1f,1f,1f,1f,   0,0,
						-1f, -1f,1f,      1f,1f,1f,1f,   0,1,
						-1f, 1f,-1f,      1f,1f,1f,1f,   1,0,
						-1f, -1f,-1f,     1f,1f,1f,1f,     1,1,
						//Right
						1f, 1f, 1f,    1f,1f,1f,1f, 0,0,
						1f, -1f,1f,   1f,1f,1f,1f, 0,1,
						1f, 1f,-1f,    1f,1f,1f,1f,        1,0,
						1f, -1f,-1f,    1f,1f,1f,1f,    1,1
				}, new int[]{
						0, 1,2,
						3,2,1,

						4, 5,6,
						7,6,5,

						8, 9,10,
						11,10,9,

						12, 13,14,
						15,14,13,

						16, 17,18,
						19,18,17,

						20, 21,22,
						23,22,21
				}, Mesh.DIMENSION_3, Mesh.RGBA, Mesh.TEXTURED) :
				new Mesh(new float[]{
						//Front
						-1f, 1f, -1f,   1f,1f,1f,1f,
						-1f, -1f,-1f,   1f,1f,1f,1f,
						1f, 1f,-1f,      1f,1f,1f,1f,
						1f, -1f,-1f,    1f,1f,1f,1f,
						//Back
						-1f, 1f, 1f,    1f,1f,1f,1f,
						-1f, -1f,1f,    1f,1f,1f,1f,
						1f, 1f,1f,     1f,1f,1f,1f,
						1f, -1f,1f,     1f,1f,1f,1f,
						//Up
						-1f, 1f, 1f,     1f,1f,1f,1f,
						-1f, 1f,-1f,    1f,1f,1f,1f,
						1f, 1f,1f,      1f,1f,1f,1f,
						1f, 1f,-1f,      1f,1f,1f,1f,
						//Down
						-1f,- 1f, 1f,   1f,1f,1f,1f,
						-1f, -1f,-1f,     1f,1f,1f,1f,
						1f, -1f,1f,       1f,1f,1f,1f,
						1f, -1f,-1f,      1f,1f,1f,1f,
						//Left
						-1f, 1f, 1f,      1f,1f,1f,1f,
						-1f, -1f,1f,      1f,1f,1f,1f,
						-1f, 1f,-1f,      1f,1f,1f,1f,
						-1f, -1f,-1f,     1f,1f,1f,1f,
						//Right
						1f, 1f, 1f,    1f,1f,1f,1f,
						1f, -1f,1f,   1f,1f,1f,1f,
						1f, 1f,-1f,    1f,1f,1f,1f,
						1f, -1f,-1f,    1f,1f,1f,1f,
				}, new int[]{
						0, 1,2,
						3,2,1,

						4, 5,6,
						7,6,5,

						8, 9,10,
						11,10,9,

						12, 13,14,
						15,14,13,

						16, 17,18,
						19,18,17,

						20, 21,22,
						23,22,21
				}, Mesh.DIMENSION_3, Mesh.RGBA, Mesh.NO_TEXTURED))), texture);
	}

	public Cube(int colorMode)
	{
		this(colorMode, null);
	}

}
