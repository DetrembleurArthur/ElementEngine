package game.jgengine.graphics.text;


import game.jgengine.debug.Logs;
import game.jgengine.graphics.Mesh;
import game.jgengine.graphics.shaders.Texture;
import game.jgengine.graphics.shapes.Rectangle;
import org.joml.Vector2f;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

public class Font
{
	private String fontPath;
	private String name;
	private int size;
	private boolean bold;
	private boolean italic;
	private String glyphFile;
	private Texture glyphsTexture;
	private HashMap<Character, Glyph> glyphs;

	public Font(String path)
	{
		glyphs = new HashMap<>();
		load(path);
	}

	private void load(String path)
	{
		try (BufferedReader br = new BufferedReader(new FileReader(path)))
		{
			this.fontPath = new File(path).getParent();
			read(br);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void read(BufferedReader br) throws IOException
	{
		String buffer = "";
		while((buffer = br.readLine()) != null && !buffer.startsWith("chars"))
		{
			var splitted = buffer.split(" ");
			for(var s : splitted)
			{
				getAttribute(s);
			}
		}

		if(buffer.startsWith("chars"))
		{
			while((buffer = br.readLine()) != null)
			{
				if(buffer.startsWith("char"))
				{
					Glyph glyph = Glyph.load(buffer);
					glyphs.put(glyph.getId(), glyph);
				}
			}
		}
	}

	private void getAttribute(String expr)
	{
		var splitted = expr.split("=");
		if(splitted.length == 2)
		{
			switch (splitted[0])
			{
				case "face":
					name = splitted[1].replace("\"", "");
					break;
				case "size":
					size = Integer.parseInt(splitted[1]);
					break;
				case "bold":
					bold = Integer.parseInt(splitted[1]) == 1;
					break;
				case "italic":
					italic = Integer.parseInt(splitted[1]) == 1;
					break;
				case "file":
					glyphFile = splitted[1].replace("\"", "");
					glyphsTexture = new Texture(fontPath + "/" + glyphFile, true);
					break;
			}
		}
	}

	public Glyph getGlyph(char id)
	{
		return glyphs.getOrDefault(id, null);
	}

	public String getFontPath()
	{
		return fontPath;
	}

	public String getName()
	{
		return name;
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

	public String getGlyphFile()
	{
		return glyphFile;
	}

	public Texture getGlyphsTexture()
	{
		return glyphsTexture;
	}

	public Mesh generateMesh(String text)
	{ /*new float[]{
					0, 0,	0, 0, //top left
					0, 1,	0, 1, //bottom left
					1, 1,	1, 1, //bottom right
					1, 0,	1, 0 //top right
			},
			new int[]{
					0, 1, 2, 0, 2, 3*/
		Vector2f glOrigin = new Vector2f(0, 1);
		Vector2f pos = new Vector2f(glOrigin.x, 0);
		float[] vertices = new float[text.length() * 4 * 4]; //4 points par char et 4 données par points
		int[] indeces = new int[6 * text.length()];
		int j = 0;
		int k = 0;
		for(int i = 0; i < text.length(); i++)
		{
			Glyph glyph = glyphs.get(text.charAt(i));
			Vector2f[] uvs = getGlyphsTexture().getUV2D(glyph);
			pos.x += glyph.getXoffset();
			pos.y = glOrigin.y - glyph.getYoffset();
			//top left
			vertices[j++] = pos.x;
			vertices[j++] = pos.y;
			vertices[j++] = uvs[0].x;
			vertices[j++] = uvs[0].y;
			//bottom left
			vertices[j++] = pos.x;
			vertices[j++] = glOrigin.y;
			vertices[j++] = uvs[1].x;
			vertices[j++] = uvs[1].y;
			//bottom right
			vertices[j++] = pos.x + glyph.getXadvance();
			vertices[j++] = glOrigin.y;
			vertices[j++] = uvs[2].x;
			vertices[j++] = uvs[2].y;
			//top right
			vertices[j++] = pos.x + glyph.getXadvance();
			vertices[j++] = pos.y;
			vertices[j++] = uvs[3].x;
			vertices[j++] = uvs[3].y;
			pos.x += glyph.getXadvance();
			//0, 1, 2, 0, 2, 3
			indeces[k++] = i * 4;
			indeces[k++] = i * 4 + 1;
			indeces[k++] = i * 4 + 2;
			indeces[k++] = i * 4;
			indeces[k++] = i * 4 + 2;
			indeces[k++] = i * 4 + 3;
		}
		return new Mesh(vertices, indeces, Mesh.DIMENSION_2, Mesh.TEXTURED);
	}
}
