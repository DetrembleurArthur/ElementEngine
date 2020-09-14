package game.jgengine.utils;

import game.jgengine.entity.Dynamic;
import game.jgengine.graphics.rendering.Renderer;

import java.util.ArrayList;
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
			dynamic.destroy();
		}
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
}
