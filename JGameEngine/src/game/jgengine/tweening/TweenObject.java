package game.jgengine.tweening;

public class TweenObject
{
	private float startValue;
	private float endValue;
	private float currentPercent;
	private TweenFunction func;

	public TweenObject(float startValue, float endValue, TweenFunction func)
	{
		this.startValue = startValue;
		this.endValue = endValue;
		this.currentPercent = 0f;
		this.func = func;
	}

	public TweenFunction getFunc()
	{
		return func;
	}

	public void setFunc(TweenFunction func)
	{
		this.func = func;
	}

	public float getStartValue()
	{
		return startValue;
	}

	public void setStartValue(float startValue)
	{
		this.startValue = startValue;
	}

	public float getEndValue()
	{
		return endValue;
	}

	public void setEndValue(float endValue)
	{
		this.endValue = endValue;
	}

	public float getCurrentPercent()
	{
		return currentPercent;
	}

	public void setCurrentPercent(float currentPercent)
	{
		if(currentPercent < 0) currentPercent = 0;
		else if(currentPercent > 1) currentPercent = 1;
		this.currentPercent = currentPercent;
	}

	public void setCurrentValue(float value)
	{
		float percent = value / startValue + (endValue - startValue);
		setCurrentPercent(percent);
	}

	public float action()
	{
		return startValue + (endValue - startValue) * func.f(currentPercent);
	}

	public boolean isFinished()
	{
		return currentPercent >= 1f;
	}

	public void swap()
	{
		float tmp = endValue;
		endValue = startValue;
		startValue = tmp;
	}
}
