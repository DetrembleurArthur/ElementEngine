package game.jgengine.graphics.shapes;

import game.jgengine.debug.Logs;
import game.jgengine.graphics.rendering.Renderer;
import game.jgengine.graphics.rendering.Texture;
import game.jgengine.tweening.TweenAction;
import game.jgengine.tweening.TweenFunctions;
import org.joml.Vector2f;

public class Slider extends Rectangle
{
	private float minValue;
	private float maxValue;
	private float currentValue;
	private Rectangle cursor;

	public Slider(float minValue, float maxValue, float currentValue, Vector2f size)
	{
		super(null);
		cursor = new Rectangle(null);
		cursor.setSize(new Vector2f(size).div(10, 0.6f));
		cursor.setCenterOrigin();
		setSize(size);
		setMinValue(minValue);
		setMaxValue(maxValue);
		setCurrentValue(currentValue);
	}

	public float getMinValue()
	{
		return minValue;
	}

	public void setMinValue(float minValue)
	{
		this.minValue = minValue;
		updateCursor();
	}

	public float getMaxValue()
	{
		return maxValue;
	}

	public void setMaxValue(float maxValue)
	{
		this.maxValue = maxValue;
		updateCursor();
	}

	public float getCurrentValue()
	{
		return currentValue;
	}

	public void setCurrentValue(float currentValue)
	{
		if(currentValue < minValue) this.currentValue = minValue;
		else if(currentValue > maxValue) this.currentValue = maxValue;
		else this.currentValue = currentValue;
		updateCursor();
	}

	public void setCurrentValue(Vector2f position)
	{
		setCurrentValue(minValue + ((maxValue-minValue) *
				TweenAction.getProgress(TweenFunctions.LINEAR, getTopLeftPosition().x, getTopLeftPosition().x + getSize().x, position.x)));
	}

	private void updateCursor()
	{
		cursor.setPosition(
				new Vector2f(
						getTopLeftPosition().x +
								(getSize().x *
										TweenAction.getProgress(TweenFunctions.LINEAR, minValue, maxValue, currentValue)),
						getCenter().y));
	}

	@Override
	public void setPosition(Vector2f position)
	{
		super.setPosition(position);
		updateCursor();
	}

	public Rectangle getCursor()
	{
		return cursor;
	}

	public float getPercent()
	{
		float shift = 0 - minValue;
		if(shift > 0)
		{
			minValue += shift;
			maxValue += shift;
		}
		float perc = TweenAction.getProgress(TweenFunctions.LINEAR, minValue, maxValue, currentValue);
		if(shift > 0)
		{
			minValue -= shift;
			maxValue -= shift;
		}
		return perc;
	}

	@Override
	public void draw(Renderer renderer)
	{
		renderer.render(this);
		renderer.render(cursor);
	}

	@Override
	public void destroy()
	{
		super.destroy();
		cursor.destroy();
	}
}
