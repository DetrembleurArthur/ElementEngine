package game.jgengine.binding;

import game.jgengine.debug.Logs;

import java.util.ArrayList;
import java.util.HashMap;

public class NotifyProperty<T> extends Property<T>
{
	private ArrayList<Listener> listeners;
	private HashMap<String, ArrayList<Listener>> fieldListeners;

	public NotifyProperty(T value)
	{
		super(value);
		listeners = new ArrayList<>();
		fieldListeners = new HashMap<>();
	}

	public void addListener(Listener listener)
	{
		listeners.add(listener);
	}

	public void addFieldListener(String fieldname, Listener listener)
	{
		if(accessibleField(fieldname))
		{
			if(!fieldListeners.containsKey(fieldname))
				fieldListeners.put(fieldname, new ArrayList<>());
			var list = fieldListeners.get(fieldname);
			list.add(listener);
		}
	}

	@Override
	public void set(T value)
	{
		super.set(value);
		if(listeners != null)
		{
			for(Listener listener : listeners)
			{
				listener.alert();
			}
		}
	}

	@Override
	public <U> void setFieldByName(String field, U object)
	{
		super.setFieldByName(field, object);
		if(fieldListeners.get(field) != null)
		{
			for(Listener listener : fieldListeners.get(field))
			{
				listener.alert();
			}
		}
	}

	public void bindBidirectional(NotifyProperty<T> property)
	{
		bind(property);
		property.bind(this);
	}

	public <U> void bindFieldBidirectional(String field, NotifyProperty<U> property)
	{
		bindField(field, property);
		property.bindField(field, this);
	}

	public <U> void bindFieldBidirectional(String field, NotifyProperty<U> property, String otherField)
	{
		bindField(field, property, otherField);
		property.bindField(otherField, this, field);
	}
}
