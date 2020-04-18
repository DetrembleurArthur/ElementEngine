package game.jgengine.utils;

import game.jgengine.exceptions.SysException;

import java.net.CookieHandler;

public class Color
{
	private int red;
	private int green;
	private int blue;
	private int alpha;

	public Color(int red, int green, int blue, int alpha)
	{
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}

	public Color(float red, float green, float blue, float alpha)
	{
		this((int)(red * 255f), (int)(green * 255f), (int)(blue * 255f), (int)(alpha * 255f));
	}

	public Color(int red, int green, int blue)
	{
		this(red, green, blue, 255);
	}

	public Color()
	{
		this(0,0,0);
	}

	public int getRed()
	{
		return red;
	}

	public float getRedRatio()
	{
		return red / 255.0f;
	}

	public void setRed(int red)
	{
		this.red = red;
	}

	public void setRed(float red)
	{
		this.red = (byte)(red * 255.0f);
	}

	public int getGreen()
	{
		return green;
	}

	public float getGreenRatio()
	{
		return green / 255.0f;
	}

	public void setGreen(int green)
	{
		this.green = green;
	}

	public void setGreen(float green)
	{
		this.green = (byte)(green * 255.0f);
	}

	public int getBlue()
	{
		return blue;
	}

	public float getBlueRatio()
	{
		return blue / 255.0f;
	}

	public void setBlue(int blue)
	{
		this.blue = blue;
	}

	public void setBlue(float blue)
	{
		this.blue = (byte)(blue * 255.0f);
	}

	public int getAlpha()
	{
		return alpha;
	}

	public float getAlphaRatio()
	{
		return alpha / 255.0f;
	}

	public void setAlpha(int alpha)
	{
		this.alpha = alpha;
	}

	public void setAlphaRatio(float alpha)
	{
		this.alpha = (byte)(alpha * 255.0f);
	}

	public Color getNegative()
	{
		return new Color( 255 - red, 255 - green, 255 - blue);
	}
}
