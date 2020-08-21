package game.jgengine.tweening;

import game.jgengine.time.Time;

import java.util.ArrayList;

public class Sequence implements Runnable
{
	private ArrayList<ActionsPack> actions;
	private int currentActionPackIndex;
	private float delay;
	private float begin;

	public Sequence()
	{
		actions = new ArrayList<>();
		currentActionPackIndex = 0;
		delay = 0f;
	}

	public void addActionPack(ActionsPack pack)
	{
		actions.add(pack);
	}

	@Override
	public void run()
	{
		ActionsPack pack = actions.get(currentActionPackIndex);
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
		for(ActionsPack pack : actions)
		{
			max += pack.getMaxTimeMs();
		}
		return max;
	}

	public boolean isFinished()
	{
		for(ActionsPack pack : actions)
		{
			if(!pack.isFinished())
				return false;
		}
		return true;
	}
}
