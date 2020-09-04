package game.jgengine.graphics.gui.widgets;

import game.jgengine.graphics.gui.event.ActionEvent;
import game.jgengine.graphics.gui.event.Valuable;
import game.jgengine.graphics.gui.event.ValueChangedEvent;
import game.jgengine.graphics.shapes.Shape;

public abstract class ValuableSmartShape<T extends Shape> extends SmartShape<T> implements Valuable
{
	protected ValuableSmartShape(T shape)
	{
		super(shape);
	}

	public void onValueChanged(ActionEvent action)
	{
		if(!onEvent(ValueChangedEvent.class, action))
		{
			addEvent(new ValueChangedEvent(this, getValue()).addActionEvent(action));
		}
	}
}
