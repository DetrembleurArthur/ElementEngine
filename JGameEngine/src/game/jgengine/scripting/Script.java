package game.jgengine.scripting;

import java.util.ArrayList;

public class Script implements Runnable
{
	private Condition condition;
	private ArrayList<Runnable> actions;

	public Script()
	{
		condition = null;
		actions = new ArrayList<>();
	}

	public Script setCondition(Condition condition)
	{
		this.condition = condition;
		return this;
	}

	public Script addAction(Runnable action)
	{
		actions.add(action);
		return this;
	}

	@Override
	public final void run()
	{
		if(condition != null)
		{
			if(condition.isTrue())
			{
				for(Runnable action : actions)
				{
					action.run();
				}
			}
		}
	}
}
