package game.jgengine.utils;

public class Size2D
{
	private int width = 0;
	private int height = 0;

	public Size2D()
	{

	}

	public Size2D(int width, int height)
	{
		this.width = width;
		this.height = height;
	}

	public Size2D(int commonSize)
	{
		this(commonSize, commonSize);
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof Size2D)
		{
			return width == ((Size2D) obj).width && height == ((Size2D) obj).height;
		}
		return false;
	}

	@Override
	public String toString()
	{
		return "Size (" + width + ", " + height + ")";
	}

	@Override
	protected Object clone()
	{
		Size2D cloned = new Size2D(width, height);
		return cloned;
	}
}
