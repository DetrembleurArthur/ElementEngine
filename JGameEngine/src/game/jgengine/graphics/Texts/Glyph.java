package game.jgengine.graphics.Texts;

import game.jgengine.graphics.shaders.Texture;
import game.jgengine.utils.VariableLoader;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector2f;

public class Glyph
{
	private char id;
	private float texX;
	private float texY;
	private Vector2f[] uvs;
	private float texWidth;
	private float texHeight;
	private float xoffset;
	private float yoffset;
	private float xadvance;

	public Glyph(@NotNull VariableLoader loader, @NotNull Texture texture)
	{
		this(	(char)loader.getInt("id"),
				loader.getFloat("x"),
				loader.getFloat("y"),
				loader.getFloat("width"),
				loader.getFloat("height"),
				loader.getFloat("xoffset"),
				loader.getFloat("yoffset"),
				loader.getFloat("xadvance"),texture);
	}

	public Glyph(char id, float texX, float texY, float texWidth, float texHeight, float xoffset, float yoffset, float xadvance, @NotNull Texture text)
	{
		this.id = id;
		this.texX = texX;
		this.texY = texY;
		this.texWidth = texWidth;
		this.texHeight = texHeight;
		this.xoffset = xoffset;
		this.yoffset = yoffset;
		this.xadvance = xadvance;
		uvs = text.getUV2D(texX, texY, texWidth, texHeight);
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
		return yoffset;
	}

	public float getXadvance()
	{
		return xadvance;
	}

	public Vector2f[] getUVs()
	{
		return uvs;
	}
}
