package game.jgengine.tweening;

import game.jgengine.time.Time;

public class TimedTweenAction extends TweenAction
{
	public static final int INFINITE_CYCLE = -1;
	private float maxDelay;
	private float beginTime = 0f;
	private int cycle = 0;
	private int maxCycle = 0;
	private boolean back = true;
	private boolean backFlag = false;

	public TimedTweenAction(float startValue, float endValue, TweenFunction func, TweenSetter binder, float maxDelay, int maxCycle, boolean back)
	{
		super(startValue, endValue, func, binder, null);
		this.maxDelay = maxDelay;
		this.maxCycle = maxCycle;
		this.back = back;
		setStepper(() ->
		{
			return (float)(Time.getTime() * 1000f - this.beginTime) / this.maxDelay;
		});
	}

	public boolean isBack()
	{
		return back;
	}

	public void setBack(boolean back)
	{
		this.back = back;
	}

	public int getMaxCycle()
	{
		return maxCycle;
	}

	public void setMaxCycle(int maxCycle)
	{
		this.maxCycle = maxCycle;
	}

	public int getCycle()
	{
		return cycle;
	}

	public void setCycle(int cycle)
	{
		this.cycle = cycle;
	}

	public void start()
	{
		beginTime = (float)Time.getTime() * 1000f;
		currentPercent = 0f;
	}

	public void restart()
	{
		start();
		setCycle(0);
		if(back)
		{
			if(backFlag)
				swap();
			backFlag = false;
		}
	}

	public void stop()
	{
		beginTime = 0f;
	}

	@Override
	public void run()
	{
		if(beginTime != 0)
		{
			super.run();
			if(isFinished())
			{

				if(back)
				{
					if(backFlag)
					{
						cycle++;
					}
				}
				else
					cycle++;
				if(cycle < maxCycle || maxCycle == INFINITE_CYCLE)
				{
					start();
					if(back)
					{
						swap();
						backFlag = !backFlag;
					}
				}
			}
		}
	}
}
