package game.jgengine.binding;

import java.util.ArrayList;

public abstract class Property<T>
{
	private ArrayList<Listener> listeners = new ArrayList<>();

	protected abstract void setValue(T value);
	public abstract T getValue();

	public final void set(T value)
	{
		setValue(value);
		if(listeners != null)
		{
			for(Listener listener : listeners)
			{
				listener.alert();
			}
		}
	}

	public final void addListener(Listener listener)
	{
		listeners.add(listener);
	}

	public void bind(Property<T> other)
	{
		addListener(() -> other.setValue(getValue()));
	}

	public void bindBidirectional(Property<T> other)
	{
		bind(other);
		other.bind(this);
	}

	public <U> void bind(Property<U> other, Converter<T, U> converter)
	{
		addListener(() -> other.setValue(converter.convert(getValue())));
	}

	public <U> void bindBidirectional(Property<U> other, Converter<T, U> converter, Converter<U, T> converterBack)
	{
		bind(other, converter);
		other.bind(this, converterBack);
	}
}
