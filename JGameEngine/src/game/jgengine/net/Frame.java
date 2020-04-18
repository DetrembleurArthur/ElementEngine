package game.jgengine.net;

import java.io.Serializable;

public class Frame implements Serializable
{
	private static final long serialVersionUID = 0L;

	private int type = 0;
	private int src = 0;
	private int dst = 0;
	private Serializable data = null;

	public Frame()
	{

	}

	public Frame(int type, int src, int dst)
	{
		this.type = type;
		this.src = src;
		this.dst = dst;
	}

	public Frame(int type, int src, int dst, Serializable data)
	{
		this.type = type;
		this.src = src;
		this.dst = dst;
		this.data = data;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public int getSrc()
	{
		return src;
	}

	public void setSrc(int src)
	{
		this.src = src;
	}

	public int getDst()
	{
		return dst;
	}

	public void setDst(int dst)
	{
		this.dst = dst;
	}

	public Serializable getData()
	{
		return data;
	}

	public void setData(Serializable data)
	{
		this.data = data;
	}

	public void swap()
	{
		int tmp = src;
		src = dst;
		dst = tmp;
	}

	public boolean isSignal()
	{
		return data == null;
	}

	@Override
	public String toString()
	{
		return type + ":" + src + ":" + dst + ":" + data;
	}
}
