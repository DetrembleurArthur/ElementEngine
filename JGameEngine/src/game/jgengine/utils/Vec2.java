package game.jgengine.utils;

class Vec2<T extends Number>
{
	public T x;
	public T y;

	public Vec2(T x, T y)
	{
		this.x = x;
		this.y = y;
	}

	public Vec2(T commonSize)
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
		if(obj instanceof Vec2f)
		{
			return x == ((Vec2f) obj).x && y == ((Vec2f) obj).y;
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
		Vec2<T> cloned = new Vec2<T>(x, y);
		return cloned;
	}
}
