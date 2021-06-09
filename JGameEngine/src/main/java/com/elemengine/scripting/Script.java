package com.elemengine.scripting;

import java.util.ArrayList;

public class Script implements Runnable
{
	private ArrayList<Runnable> actions;

	public Script()
	{
		actions = new ArrayList<>();
	}

	public Script addAction(Runnable action)
	{
		actions.add(action);
		return this;
	}

	@Override
	public void run()
	{
		for(Runnable action : actions)
		{
			action.run();
		}
	}
}
