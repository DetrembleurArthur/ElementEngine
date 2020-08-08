package game.jgengine.graphics.gui.widgets;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.shapes.Slider;
import game.jgengine.sys.Game;
import org.joml.Vector2f;

public class WSlider extends ValuableWidget<Slider>
{
	public WSlider(float mi, float ma, float cu, Vector2f size)
	{
		super(new Slider(mi, ma, cu, size));
		onMouseLeftButtonClicked(sender -> {
			getShape().setCurrentValue(Mouse.getPosition((Camera2D)Game.GAME.getCurrentScene().getCamera()));
		}, true);
	}

	@Override
	public Object getValue()
	{
		return getShape().getCurrentValue();
	}
}
