package game.jgengine.utils;

import game.jgengine.debug.Logs;
import game.jgengine.entity.Dynamic;
import game.jgengine.graphics.rendering.Renderer;

import java.util.ArrayList;
import java.util.Collection;

public class LaterList<T extends Dynamic> extends ArrayList<T> implements Dynamic
{
	private ArrayList<T> addLaterList;
	private ArrayList<T> removeLaterList;

	public LaterList()
	{
		super();
		addLaterList = new ArrayList<>();
		removeLaterList = new ArrayList<>();
	}

	public void addLater(T dynamic)
	{
		addLaterList.add(dynamic);
	}

	public void removeLater(T dynamic)
	{
		removeLaterList.add(dynamic);
	}

	public void addLater(Collection<? extends T> dynamic)
	{
		addLaterList.addAll(dynamic);
	}

	public void removeLater(Collection<? extends T> dynamic)
	{
		removeLaterList.addAll(dynamic);
	}

	public void sync()
	{
		removeAll(removeLaterList);
		addAll(addLaterList);
		removeLaterList.clear();
		addLaterList.clear();
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
