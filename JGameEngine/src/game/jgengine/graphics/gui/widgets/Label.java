package game.jgengine.graphics.gui.widgets;

import game.jgengine.binding.Property;
import game.jgengine.graphics.texts.Font;
import game.jgengine.graphics.texts.Text;

public class Label extends ValuableSmartShape<Text>
{
	public final Property<String> textProperty = new Property<>()
	{
		@Override
		public void setValue(String value)
		{
			getShape().setText(value);
		}

		@Override
		public String getValue()
		{
			return getShape().getText();
		}
	};

	public Label(Font font, String text)
	{
		super(new Text(font, text));
	}

	@Override
	public Object getValue()
	{
		return textProperty.getValue();
	}
}
