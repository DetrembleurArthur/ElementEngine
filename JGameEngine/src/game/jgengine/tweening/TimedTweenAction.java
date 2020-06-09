package game.jgengine.tweening;

import game.jgengine.utils.Time;

public class TimedTweenAction extends TweenAction
{
	private float maxDelay;
	private float beginTime = 0f;

	public TimedTweenAction(TweenObject tweenObject, TweenSetter property, float msMaxDelay)
	{
		super(tweenObject, property, null);
		setCounter(() ->
		{
			return (float)(Time.getTime() * 1000f - this.beginTime) / this.maxDelay;
		});
		maxDelay = msMaxDelay;
	}

	public void start()
	{
		beginTime = (float)Time.getTime() * 1000f;
	}

	public void stop()
	{
		beginTime = 0f;
	}

	@Override
	public void run()
	{
		if(beginTime != 0)
			super.run();
	}
}
