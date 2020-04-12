package game.jgengine.utils;

class Point2D<T extends Number>
{
	private T x;
	private T y;

	public Point2D(T x, T y)
	{
		this.x = x;
		this.y = y;
	}

	public Point2D(T commonSize)
	{
		this(commonSize, commonSize);
	}

	public T getX()
	{
		return x;
	}

	public void setX(T x)
	{
		this.x = x;
	}

	public T getY()
	{
		return y;
	}

	public void setY(T y)
	{
		this.y = y;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof Point2D)
		{
			return x == ((Point2D) obj).x && y == ((Point2D) obj).y;
		}
		return false;
	}

	@Override
	public String toString()
	{
		return "Point2D (" + x + ", " + y + ")";
	}

	@Override
	protected Object clone()
	{
		Point2D cloned = new Point2D(x, y);
		return cloned;
	}
}
