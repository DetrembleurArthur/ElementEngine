package game.jgengine.tweening;

public class TweenAction implements Runnable
{
	private TweenObject tweenObject;
	private TweenSetter property;
	private TweenGetter counter;

	public TweenAction(TweenObject tweenObject, TweenSetter property, TweenGetter counter)
	{
		this.tweenObject = tweenObject;
		this.property = property;
		this.counter = counter;
	}

	public TweenGetter getCounter()
	{
		return counter;
	}

	public void setCounter(TweenGetter counter)
	{
		this.counter = counter;
	}

	public TweenSetter getProperty()
	{
		return property;
	}

	public void setProperty(TweenSetter property)
	{
		this.property = property;
	}

	public TweenObject getTweenObject()
	{
		return tweenObject;
	}

	public void setTweenObject(TweenObject tweenObject)
	{
		this.tweenObject = tweenObject;
	}

	@Override
	public void run()
	{
		tweenObject.setCurrentPercent(counter.get());
		property.set(tweenObject.action());
	}
}
