package com.elemengine.components.properties;

import com.elemengine.binding.FloatProperty;
import com.elemengine.components.event.Valuable;
import com.elemengine.entity.GameObject;
import com.elemengine.components.Component;

public class ValuePropertyComponent extends Component
{
	public final FloatProperty valueProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			((Valuable)getRelativeObject()).setValue(value);
		}

		@Override
		public Float getValue()
		{
			return (Float) ((Valuable)getRelativeObject()).getValue();
		}
	};

	public ValuePropertyComponent(GameObject relativeObject)
	{
		super(relativeObject);
		if(!(relativeObject instanceof Valuable))
			throw new RuntimeException("relativeObject must be a Valuable");
	}
}
