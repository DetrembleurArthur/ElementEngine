package game.jgengine.graphics.Texts;

import game.jgengine.debug.Logs;
import game.jgengine.graphics.Mesh;
import game.jgengine.graphics.shaders.Texture;
import game.jgengine.utils.VariableLoader;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.lwjgl.system.CallbackI;

import java.io.*;
import java.util.HashMap;

public class Font
{
	/*
	Normaliser: height = height / size
				width = width / size
				+ offsets
	*/
	private String fontPath;
	private String face;
	private int size;
	private boolean bold;
	private boolean italic;
	private String texFile;
	private Texture textureAtlas;
	private float[] padding;//up-right-down-left
	private float[] spacing;
	private float lineHeight;
	private float base;
	private float scaleW;
	private float scaleH;
	private HashMap<Character, Glyph> glyphs;

	public Font(String fontPath)
	{
		this.fontPath = fontPath;
		glyphs = new HashMap<>();
		load();
	}

	private void setInfoFromLoader(VariableLoader loader)
	{
		face = loader.getString("face").replace("\"", "");
		size = loader.getInt("size");
		bold = loader.getBool("bold");
		italic = loader.getBool("italic");
		texFile = loader.getString("file").replace("\"", "");
		padding = loader.getFloatA("padding", 4);
		spacing = loader.getFloatA("spacing", 2);
		lineHeight = loader.getFloat("lineHeight");
		base = loader.getFloat("base");
		scaleW = loader.getFloat("scaleW");
		scaleH = loader.getFloat("scaleH");
		Logs.print(new File(fontPath).getParent() + "/"+texFile);
		textureAtlas = new Texture(new File(fontPath).getParent() + "/"+texFile, true);
	}

	private void load()
	{
		try (BufferedReader br = new BufferedReader(new FileReader(fontPath)))
		{
			VariableLoader loader = new VariableLoader();
			String buf;
			while((buf = br.readLine()) != null && !buf.startsWith("chars"))
			{
				loader.load(buf);
			}
			setInfoFromLoader(loader);
			while((buf = br.readLine()) != null)
			{
				if(buf.startsWith("char"))
				{
					loader.clean();
					loader.load(buf);
					glyphs.put((char)loader.getInt("id"), new Glyph(loader, textureAtlas));
				}
			}
			loader.clean();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public Mesh generateMesh(String text)
	{
		var len = text.length();
		float[] vertices = new float[len * 4];
		int[] indices = new int[len * 6];
		Vector2i counters = new Vector2i(0, 0);
		Vector2f cursorPos = new Vector2f(0, 0);
		Vector2f pos = new Vector2f();
		Vector2f maxPos = new Vector2f();
		Vector2f[] uvs;
		for(int i = 0; i < len; i++)
		{
			Glyph glyph = glyphs.get(text.charAt(i));
			uvs = glyph.getUVs();
			pos.x = cursorPos.x + glyph.getXoffset();
			pos.y = cursorPos.y + glyph.getYoffset();
			maxPos.x = pos.x + glyph.getTexWidth();
			maxPos.y = pos.y + glyph.getTexHeight();

			addVertex(counters, vertices, pos, uvs[0]); //top left
			addVertex(counters, vertices, new Vector2f(pos.x, maxPos.y), uvs[1]); //bottom left
			addVertex(counters, vertices, maxPos, uvs[0]); //bottom right
			addVertex(counters, vertices, new Vector2f(maxPos.x, pos.y), uvs[0]); //top right

			addIndex(counters, indices, i);

			cursorPos.x += glyph.getXadvance();
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

	public String getFontPath()
	{
		return fontPath;
	}

	public String getFace()
	{
		return face;
	}

	public int getSize()
	{
		return size;
	}

	public boolean isBold()
	{
		return bold;
	}

	public boolean isItalic()
	{
		return italic;
	}

	public float[] getPadding()
	{
		return padding;
	}

	public float[] getSpacing()
	{
		return spacing;
	}

	public float getLineHeight()
	{
		return lineHeight;
	}

	public float getBase()
	{
		return base;
	}

	public float getScaleW()
	{
		return scaleW;
	}

	public float getScaleH()
	{
		return scaleH;
	}
}