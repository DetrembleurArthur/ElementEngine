package game.jgengine.graphics.gui.widgets;

import game.jgengine.graphics.shapes.ProgressBar;
import org.joml.Vector2f;

public class WProgressBar extends ValuableWidget<ProgressBar>
{
	public WProgressBar(float minValue, float maxValue, float currentValue, Vector2f maxSize)
	{
		super(new ProgressBar(minValue, maxValue, currentValue, maxSize));
	}

	@Override
	public Object getValue()
	{
		return getShape().getCurrentValue();
	}
}
