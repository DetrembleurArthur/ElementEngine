package game.jgengine.graphics.gui.widgets;

import game.jgengine.binding.FloatProperty;
import game.jgengine.binding.Property;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.shapes.Slider;
import game.jgengine.sys.Game;
import org.joml.Vector2f;

public class WSlider extends ValuableWidget<Slider>
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

	public final Property<Vector2f> mouseXValueProperty = new Property<>()
	{
		@Override
		public void setValue(Vector2f value)
		{
			getShape().setCurrentValue(value);
			valueProperty.alertListeners();
		}

		@Override
		public Vector2f getValue()
		{
			return null;
		}
	};

	public WSlider(float mi, float ma, float cu, Vector2f size)
	{
		super(new Slider(mi, ma, cu, size));
		onMouseLeftButtonClicked(sender -> {
			mouseXValueProperty.set(Mouse.getPosition((Camera2D)Game.GAME.getCurrentScene().getCamera()));
		}, true);
	}

	@Override
	public Object getValue()
	{
		return valueProperty.getValue();
	}
}
