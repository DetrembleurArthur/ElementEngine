package game.jgengine.components.properties;

import game.jgengine.binding.Property;
import game.jgengine.components.Component;
import game.jgengine.components.event.Valuable;
import game.jgengine.entity.GameObject;
import game.jgengine.graphics.texts.Text;

public class TextPropertyComponent extends Component
{
	public final Property<String> textProperty = new Property<>()
	{
		@Override
		public void setValue(String value)
		{
			((Text)getRelativeObject()).setText(value);
		}

		@Override
		public String getValue()
		{
			return ((Text)getRelativeObject()).getText();
		}
	};

	public TextPropertyComponent(GameObject relativeObject)
	{
		super(relativeObject);
		if(!(relativeObject instanceof Text))
			throw new RuntimeException("relativeObject must be a Text");
	}
}
