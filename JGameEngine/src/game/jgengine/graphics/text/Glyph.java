package game.jgengine.graphics.text;

import org.joml.Vector2f;

public class Glyph
{
	private char id;
	private float texX;
	private float texY;
	private float texWidth;
	private float texHeight;
	private float quadWidth;
	private float quadHeight;
	private float xoffset;
	private float yoffset;
	private float xadvance;


	private Glyph()
	{

	}

	public float getQuadWidth()
	{
		return quadWidth;
	}

	public float getQuadHeight()
	{
		return quadHeight;
	}

	public void adapt(Font font)
	{
		var padding = new float[]{0, 0, 0, 0};//font.getPadding();
		this.texX = (texX) / font.getScaleW();
		this.texY = (texY) / font.getScaleH();
		float width = texWidth;
		float height = texHeight;
		quadWidth = width * font.getHorizontalPerPixelSize();
		quadHeight = height * font.getVerticalPerPixelSize();
		texWidth = width / font.getScaleW();
		texHeight = height / font.getScaleH();
		xoffset = (xoffset) * font.getHorizontalPerPixelSize();
		yoffset = (yoffset) * font.getVerticalPerPixelSize();
		xadvance = (xadvance) * font.getHorizontalPerPixelSize();
	}

	public Glyph(char id, int texX, int texY, int texWidth, int texHeight, int xoffset, int yoffset, int xadvance)
	{
		this.id = id;
		this.texX = texX;
		this.texY = texY;
		this.texWidth = texWidth;
		this.texHeight = texHeight;
		this.xoffset = xoffset;
		this.yoffset = yoffset;
		this.xadvance = xadvance;
	}

	public char getId()
	{
		return id;
	}

	public float getTexX()
	{
		return texX;
	}

	public float getTexY()
	{
		return texY;
	}

	public float getTexWidth()
	{
		return texWidth;
	}

	public float getTexHeight()
	{
		return texHeight;
	}

	public float getXoffset()
	{
		return xoffset;
	}

	public float getYoffset()
	{
		return /*37-*/yoffset;
	}

	public float getXadvance()
	{
		return xadvance;
	}

	public static Glyph load(String fntGlyphLine)
	{
		var splitted = fntGlyphLine.split(" ");
		Glyph glyph = new Glyph();
		for(var s : splitted)
		{
			if(s.length() > 1)
			{
				var attr = s.split("=");
				if(attr.length == 2)
				{
					switch(attr[0])
					{
						case "id":
							glyph.id = (char) Integer.parseInt(attr[1]);
							break;

						case "x":
							glyph.texX = Integer.parseInt(attr[1]);
							break;

						case "y":
							glyph.texY = Integer.parseInt(attr[1]);
							break;

						case "width":
							glyph.texWidth = Integer.parseInt(attr[1]);
							break;

						case "height":
							glyph.texHeight = Integer.parseInt(attr[1]);
							break;

						case "xoffset":
							glyph.xoffset = Integer.parseInt(attr[1]);
							break;

						case "yoffset":
							glyph.yoffset = Integer.parseInt(attr[1]);
							break;

						case "xadvance":
							glyph.xadvance = Integer.parseInt(attr[1]);
							break;
					}
				}
			}
		}
		return glyph;
	}

	public Vector2f[] getUVs()
	{
		return new Vector2f[]
		{
				new Vector2f(getTexX(), getTexY()),
				new Vector2f(getTexX(), getTexY() + getTexHeight()),
				new Vector2f(getTexX() + getTexWidth(), getTexY() + getTexHeight()),
				new Vector2f(getTexX() + getTexWidth(), getTexY())
		};
	}

	@Override
	public String toString()
	{
		return this.id + ", " + this.texX + ", " + this.texY + ", " + this.texWidth + ", " + this.texHeight + ", " + this.xoffset + ", " + this.yoffset + ", " + this.xadvance;
	}
}
