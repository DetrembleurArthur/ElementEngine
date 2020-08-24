package game.jgengine.graphics.gui.widgets;

import game.jgengine.binding.FloatProperty;
import game.jgengine.graphics.shapes.ProgressBar;
import org.joml.Vector2f;

public class WProgressBar extends ValuableWidget<ProgressBar>
{
	public final FloatProperty valueProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			getShape().setCurrentValue(value);
		}

		@Override
		public Float getValue()
		{
			return getShape().getCurrentValue();
		}
	};

	public WProgressBar(float minValue, float maxValue, float currentValue, Vector2f maxSize)
	{
		super(new ProgressBar(minValue, maxValue, currentValue, maxSize));
	}

	@Override
	public Object getValue()
	{
		return valueProperty.getValue();
	}
}
