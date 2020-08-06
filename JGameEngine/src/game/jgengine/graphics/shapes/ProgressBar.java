package game.jgengine.graphics.shapes;

import game.jgengine.debug.Logs;
import game.jgengine.graphics.rendering.Texture;
import game.jgengine.sys.Game;
import game.jgengine.tweening.TweenAction;
import game.jgengine.tweening.TweenFunction;
import game.jgengine.tweening.TweenFunctions;
import game.jgengine.utils.Colors;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class ProgressBar extends Rectangle
{
	private float minValue;
	private float maxValue;
	private float currentValue;
	private Vector2f maxSize;
	private Vector2f currentSize;
	private TweenFunction widthProgressFunction;

	public ProgressBar(float minValue, float maxValue, float currentValue, Vector2f maxSize)
	{
		super(null);
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.currentValue = currentValue;
		this.maxSize = maxSize;
		this.widthProgressFunction = TweenFunctions.LINEAR;
		setSize(maxSize);
		updateRectangle();
	}

	public void updateRectangle()
	{
		currentSize = new Vector2f(maxSize.x * getPercent(), maxSize.y);
	}

	@Override
	public void draw()
	{
		super.draw();
	}

	public float getMinValue()
	{
		return minValue;
	}

	public void setMinValue(float minValue)
	{
		this.minValue = minValue;
		updateRectangle();
	}

	public float getMaxValue()
	{
		return maxValue;
	}

	public void setMaxValue(float maxValue)
	{
		this.maxValue = maxValue;
		updateRectangle();
	}

	public float getCurrentValue()
	{
		return currentValue;
	}

	public void setCurrentValue(float currentValue)
	{
		if(currentValue < minValue) currentValue = minValue;
		else if(currentValue > maxValue) currentValue = maxValue;
		this.currentValue = currentValue;
		updateRectangle();
	}

	public Vector2f getMaxSize()
	{
		return maxSize;
	}

	public void setMaxSize(Vector2f maxSize)
	{
		this.maxSize = maxSize;
	}

	public TweenFunction getWidthProgressFunction()
	{
		return widthProgressFunction;
	}

	public void setWidthProgressFunction(TweenFunction widthProgressFunction)
	{
		this.widthProgressFunction = widthProgressFunction;
	}

	public float getPercent()
	{
		float shift = 0 - minValue;
		if(shift > 0)
		{
			minValue += shift;
			maxValue += shift;
		}
		float perc = TweenAction.get(widthProgressFunction, minValue, maxValue, currentValue) / 100;
		if(shift > 0)
		{
			minValue -= shift;
			maxValue -= shift;
		}
		return perc;
	}
}
