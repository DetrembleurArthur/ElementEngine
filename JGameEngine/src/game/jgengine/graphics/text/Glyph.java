package game.jgengine.graphics.text;

public class Glyph
{
	private char id;
	private int x;
	private int y;
	private int width;
	private int height;
	private int xoffset;
	private int yoffset;
	private int xadvance;

	private Glyph()
	{

	}

	public Glyph(char id, int x, int y, int width, int height, int xoffset, int yoffset, int xadvance)
	{
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.xoffset = xoffset;
		this.yoffset = yoffset;
		this.xadvance = xadvance;
	}

	public char getId()
	{
		return id;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public int getXoffset()
	{
		return xoffset;
	}

	public int getYoffset()
	{
		return /*37-*/yoffset;
	}

	public int getXadvance()
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
							glyph.x = Integer.parseInt(attr[1]);
							break;

						case "y":
							glyph.y = Integer.parseInt(attr[1]);
							break;

						case "width":
							glyph.width = Integer.parseInt(attr[1]);
							break;

						case "height":
							glyph.height = Integer.parseInt(attr[1]);
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

	@Override
	public String toString()
	{
		return this.id + ", " + this.x + ", " + this.y + ", " + this.width + ", " + this.height + ", " + this.xoffset + ", " + this.yoffset + ", " + this.xadvance;
	}
}
