package game.jgengine.graphics.gui.widgets;

import game.jgengine.graphics.texts.Font;
import game.jgengine.graphics.texts.Text;

public class Label extends ValuableWidget<Text>
{
	public Label(Font font, String text)
	{
		super(new Text(font, text));
	}

	@Override
	public Object getValue()
	{
		return getShape().getText();
	}
}
