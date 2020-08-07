package game.jgengine.graphics.gui.widgets;

import game.jgengine.graphics.shapes.Progress;
import org.joml.Vector2f;

public class ProgressBar extends ValuableWidget<Progress>
{
	public ProgressBar(float minValue, float maxValue, float currentValue, Vector2f maxSize)
	{
		super(new Progress(minValue, maxValue, currentValue, maxSize));
	}

	@Override
	public Object getValue()
	{
		return getShape().getCurrentValue();
	}
}
