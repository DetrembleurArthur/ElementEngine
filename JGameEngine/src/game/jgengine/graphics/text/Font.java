package game.jgengine.graphics.text;


import game.jgengine.debug.Logs;
import game.jgengine.graphics.Mesh;
import game.jgengine.graphics.shaders.Texture;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.sys.Window;
import org.joml.Vector2f;
import org.joml.Vector4f;

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
	private int stretchH;
	private float[] padding;
	public static final int PAD_TOP = 0;
	public static final int PAD_LEFT = 1;
	public static final int PAD_BOTTOM = 2;
	public static final int PAD_RIGHT = 3;
	public static final int DESIRED_PADDING = 0;
	private float paddingWidth;
	private float paddingHeight;
	private Vector2f spacing;
	private int lineHeight;
	private int base;
	private int scaleW;
	private int scaleH;
	private float verticalPerPixelSize;
	private float horizontalPerPixelSize;
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
		verticalPerPixelSize = Window.WINDOW.getAspectRatio()/ 96f;
		horizontalPerPixelSize = Window.WINDOW.getAspectRatio() / 96f;
		/*
		    verticalPerPixelSize = TextMeshCreator.LINE_HEIGHT / (double) lineHeightPixels;
			horizontalPerPixelSize = verticalPerPixelSize / aspectRatio
		* */

		if(buffer.startsWith("chars"))
		{
			while((buffer = br.readLine()) != null)
			{
				if(buffer.startsWith("char"))
				{
					Glyph glyph = Glyph.load(buffer);
					glyph.adapt(this);
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
					glyphsTexture = new Texture(fontPath + "/"+glyphFile, true);
					break;
				case "stretchH":
					stretchH = Integer.parseInt(splitted[1]);
					break;
				case "padding":
					var values = splitted[1].split(",");
					if(values.length == 4)
						padding = new float[]{Float.parseFloat(values[0]),Float.parseFloat(values[1]),Float.parseFloat(values[2]),Float.parseFloat(values[3])};
					paddingWidth = padding[PAD_LEFT] + padding[PAD_RIGHT];
					paddingHeight = padding[PAD_TOP] + padding[PAD_BOTTOM];
					break;
				case "spacing":
					values = splitted[1].split(",");
					if(values.length == 2)
						spacing = new Vector2f(Float.parseFloat(values[0]),Float.parseFloat(values[1]));
					break;
				case "lineHeight":
					lineHeight = Integer.parseInt(splitted[1]);

					break;
				case "base":
					base = Integer.parseInt(splitted[1]);
					break;
				case "scaleW":
					scaleW = Integer.parseInt(splitted[1]);
					break;
				case "scaleH":
					scaleH = Integer.parseInt(splitted[1]);
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

	public int getStretchH()
	{
		return stretchH;
	}

	public float[] getPadding()
	{
		return padding;
	}

	public float getPaddingWidth()
	{
		return paddingWidth;
	}

	public float getPaddingHeight()
	{
		return paddingHeight;
	}

	public Vector2f getSpacing()
	{
		return spacing;
	}

	public int getLineHeight()
	{
		return lineHeight;
	}

	public int getBase()
	{
		return base;
	}

	public int getScaleW()
	{
		return scaleW;
	}

	public int getScaleH()
	{
		return scaleH;
	}

	public float getVerticalPerPixelSize()
	{
		return verticalPerPixelSize;
	}

	public float getHorizontalPerPixelSize()
	{
		return horizontalPerPixelSize;
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
		Vector2f pos = new Vector2f(0, 0);
		float[] vertices = new float[text.length() * 4 * 4]; //4 points par char et 4 données par points
		int[] indeces = new int[6 * text.length()];
		int j = 0;
		int k = 0;


		Logs.print(size);
		for(int i = 0; i < text.length(); i++)
		{
			Glyph glyph = glyphs.get(text.charAt(i));
			Vector2f[] uvs = glyph.getUVs();
			//top left
			float x = pos.x + glyph.getXoffset() * size;
			float y = pos.y + glyph.getYoffset() * size;
			float maxX = x + glyph.getQuadWidth() * size;
			float maxY = y + glyph.getQuadHeight() * size;
			Logs.print(glyph.getQuadWidth() + " " + glyph.getQuadHeight());
			vertices[j++] = x;
			vertices[j++] = y;
			vertices[j++] = uvs[0].x;
			vertices[j++] = uvs[0].y;
			//bottom left
			vertices[j++] = x;
			vertices[j++] = maxY;
			vertices[j++] = uvs[1].x;
			vertices[j++] = uvs[1].y;
			//bottom right
			vertices[j++] = maxX;
			vertices[j++] = maxY;
			vertices[j++] = uvs[2].x;
			vertices[j++] = uvs[2].y;
			//top right
			vertices[j++] = maxX;
			vertices[j++] = y;
			vertices[j++] = uvs[3].x;
			vertices[j++] = uvs[3].y;
			pos.x += glyph.getXadvance() * size;
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
