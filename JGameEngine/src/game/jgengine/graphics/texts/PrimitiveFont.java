package game.jgengine.graphics.texts;

import game.jgengine.graphics.vertex.Mesh;
import org.joml.Vector2f;
import org.joml.Vector2i;

import java.util.ArrayList;
import java.util.HashMap;

public class PrimitiveFont
{
	private HashMap<Character, ArrayList<Vector2f>>  glyphs;
	private float padding = 3f;

	public PrimitiveFont()
	{
		glyphs = new HashMap<>();
	}

	public void setChar(Character c, Character[][] atlas)
	{
		ArrayList<Vector2f> list = new ArrayList<>();
		glyphs.put(c, list);
		for(int i = 0; i < atlas.length; i++)
		{
			for(int j = 0; j < atlas[i].length; j++)
			{
				if(atlas[i][j] != ' ')
				{
					list.add(new Vector2f(j, i));
				}
			}
		}
	}

	public int getNumberOfSquares(String text)
	{
		int n = 0;
		for(Character c : text.toCharArray())
		{
			var glyph = glyphs.get(c);
			if(glyph != null)
			{
				n += glyph.size();
			}
		}
		return n;
	}

	public Mesh generateMesh(String text)
	{
		int n = getNumberOfSquares(text);
		float[] vertices = new float[n * 4 * 4];
		int[] indices = new int[n * 6];
		Vector2i counters = new Vector2i();
		int i = 0;
		Vector2f max = new Vector2f();
		for(Character c : text.toCharArray())
		{
			var glyph = glyphs.get(c);
			if(glyph != null)
			{
				Vector2f lastMax = new Vector2f(max);
				for(Vector2f pos : glyph)
				{
					pos = new Vector2f(pos);
					pos.add(lastMax);
					if(max.x < pos.x) max.x = pos.x;
					addVertex(counters, vertices, pos, pos); //top left
					addVertex(counters, vertices, new Vector2f(pos.x, pos.y + 1), new Vector2f(pos.x, pos.y + 1)); //bottom left
					addVertex(counters, vertices, new Vector2f(pos.x + 1, pos.y + 1), new Vector2f(pos.x + 1, pos.y + 1)); //bottom right
					addVertex(counters, vertices, new Vector2f(pos.x + 1, pos.y), new Vector2f(pos.x + 1, pos.y)); //top right
					addIndex(counters, indices, i++);
				}
				max.x += padding;
			}
		}
		return new Mesh(vertices, indices, Mesh.DIMENSION_2, Mesh.TEXTURED);
	}

	private void addVertex(Vector2i counters, float[] vertices, Vector2f pos, Vector2f uv)
	{
		vertices[counters.x++] = pos.x;
		vertices[counters.x++] = pos.y;
		vertices[counters.x++] = uv.x;
		vertices[counters.x++] = uv.y;
	}

	private void addIndex(Vector2i counters, int[] indices, int i)
	{
		indices[counters.y++] = i * 4;
		indices[counters.y++] = i * 4 + 1;
		indices[counters.y++] = i * 4 + 2;
		indices[counters.y++] = i * 4;
		indices[counters.y++] = i * 4 + 2;
		indices[counters.y++] = i * 4 + 3;
	}
}
