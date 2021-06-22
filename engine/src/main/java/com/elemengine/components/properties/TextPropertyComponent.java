package com.elemengine.components.properties;

import com.elemengine.binding.Property;
import com.elemengine.entity.GameObject;
import com.elemengine.graphics.texts.fnt.Text;
import com.elemengine.components.Component;

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
