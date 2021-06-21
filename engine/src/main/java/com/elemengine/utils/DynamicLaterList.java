package com.elemengine.utils;

import com.elemengine.graphics.rendering.Renderer;
import com.elemengine.entity.Dynamic;

import java.util.Collection;

public class DynamicLaterList extends LaterList<Dynamic> implements Dynamic
{
	public DynamicLaterList()
	{
		super();
	}

	@Override
	public void run()
	{
		for(var dynamic : this)
		{
			dynamic.run();
		}
		sync();
	}

	@Override
	public void destroy()
	{
		for(var dynamic : this)
		{
			if(dynamic != null)
				dynamic.destroy();
		}
		clear();
	}

	@Override
	public void draw()
	{
		for(var dynamic : this)
		{
			dynamic.draw();
		}
	}

	@Override
	public void draw(Renderer renderer)
	{
		for(var dynamic : this)
		{
			dynamic.draw(renderer);
		}
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		c.forEach(o -> ((Dynamic)o).destroy());
		return super.removeAll(c);
	}
}
