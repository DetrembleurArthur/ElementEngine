package game.jgengine.tweening;

import game.jgengine.time.Time;

import java.util.ArrayList;

public class AnimationsSequence implements Runnable
{
	private ArrayList<AnimationsPack> actions;
	private int currentActionPackIndex;
	private float delay;
	private float begin;

	public AnimationsSequence()
	{
		actions = new ArrayList<>();
		currentActionPackIndex = 0;
		delay = 0f;
	}

	public void addActionPack(AnimationsPack pack)
	{
		actions.add(pack);
	}

	@Override
	public void run()
	{
		AnimationsPack pack = actions.get(currentActionPackIndex);
		if(!pack.isFinished())
		{
			pack.run();
		}
		else
		{
			if(currentActionPackIndex + 1 < actions.size())
			{
				currentActionPackIndex++;
				actions.get(currentActionPackIndex).start();
			}
		}
	}

	public void start()
	{
		delay = calculateDelay();
		begin = (float) (Time.getTime() * 1000f);
		actions.get(0).start();
	}

	public float getDelay()
	{
		return delay;
	}

	public float getCurrentTime()
	{
		return (float) ((Time.getTime() * 1000f) - begin);
	}

	public float getCurrentPercent()
	{
		return getCurrentTime() / delay;
	}

	public float calculateDelay()
	{
		float max = 0;
		for(AnimationsPack pack : actions)
		{
			max += pack.getMaxTimeMs();
		}
		return max;
	}

	public boolean isFinished()
	{
		for(AnimationsPack pack : actions)
		{
			if(!pack.isFinished())
				return false;
		}
		return true;
	}
}
