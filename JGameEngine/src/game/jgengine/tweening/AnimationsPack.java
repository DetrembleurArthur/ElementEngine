package game.jgengine.tweening;

import java.util.ArrayList;

public class AnimationsPack implements Runnable
{
	private ArrayList<TimedTweenAction> actions;

	public AnimationsPack()
	{
		actions = new ArrayList<>();
	}

	public AnimationsPack addAction(TimedTweenAction action)
	{
		actions.add(action);
		return this;
	}

	public void start()
	{
		for(TimedTweenAction action : actions)
		{
			action.start();
		}
	}

	public boolean isFinished()
	{
		for(TimedTweenAction action : actions)
		{
			if(!action.isFinished())
				return false;
		}
		return true;
	}

	@Override
	public void run()
	{
		for(TimedTweenAction action : actions)
		{
			action.run();
		}
	}

	public float getMaxTimeMs()
	{
		float max = 0;
		for(TimedTweenAction action : actions)
		{
			if(action.getMaxDelay() > max)
				max = action.getMaxDelay();
		}
		return max;
	}
}
