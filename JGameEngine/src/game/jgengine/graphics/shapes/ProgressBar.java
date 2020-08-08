package game.jgengine.graphics.shapes;

import game.jgengine.debug.Logs;
import game.jgengine.graphics.rendering.Renderer;
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
	private Vector4f minColor;
	private Vector4f maxColor;
	private Vector4f backgroundColor;
	private TweenFunction colorProgressFunction;

	public ProgressBar(float minValue, float maxValue, float currentValue, Vector2f maxSize)
	{
		super(null);
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.currentValue = currentValue;
		this.maxSize = maxSize;
		this.widthProgressFunction = TweenFunctions.LINEAR;
		this.colorProgressFunction = TweenFunctions.LINEAR;
		this.minColor = Colors.RED;
		this.maxColor = Colors.LIME;
		this.backgroundColor = Colors.BLACK;
		setSize(maxSize);
		updateRectangle();
	}

	public void updateRectangle()
	{
		currentSize = new Vector2f(maxSize.x * getPercent(), maxSize.y);
	}

	@Override
	public void draw(Renderer renderer)
	{
		renderer.render(this);
		setFillColor(getProgressColor());
		setSize(currentSize);
		renderer.render(this);
		setFillColor(backgroundColor);
		setSize(maxSize);
	}


	public Vector4f getProgressColor()
	{
		return Colors.interpolate(minColor, maxColor, getPercent(), colorProgressFunction);
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

	public void setCurrentPercent(float percent)
	{
		setCurrentValue(percent * (maxValue - minValue) + minValue);
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

	public TweenFunction getColorProgressFunction()
	{
		return colorProgressFunction;
	}

	public void setColorProgressFunction(TweenFunction colorProgressFunction)
	{
		this.colorProgressFunction = colorProgressFunction;
	}

	public float getPercent()
	{
		float shift = 0 - minValue;
		if(shift > 0)
		{
			minValue += shift;
			maxValue += shift;
		}
		float perc = TweenAction.getProgress(widthProgressFunction, minValue, maxValue, currentValue);
		if(shift > 0)
		{
			minValue -= shift;
			maxValue -= shift;
		}
		return perc;
	}

	public Vector4f getMinColor()
	{
		return minColor;
	}

	public void setMinColor(Vector4f minColor)
	{
		this.minColor = minColor;
	}

	public Vector4f getMaxColor()
	{
		return maxColor;
	}

	public void setMaxColor(Vector4f maxColor)
	{
		this.maxColor = maxColor;
	}

	public Vector4f getBackgroundColor()
	{
		return backgroundColor;
	}

	public void setBackgroundColor(Vector4f backgroundColor)
	{
		this.backgroundColor = backgroundColor;
	}
}
