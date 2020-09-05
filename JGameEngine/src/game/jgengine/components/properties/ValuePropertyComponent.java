package game.jgengine.components.properties;

import game.jgengine.binding.FloatProperty;
import game.jgengine.components.Component;
import game.jgengine.components.event.Valuable;
import game.jgengine.debug.Logs;
import game.jgengine.entity.GameObject;

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
