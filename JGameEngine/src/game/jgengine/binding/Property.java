package game.jgengine.binding;

import java.lang.reflect.Field;
import java.util.ArrayList;

public abstract class Property<T>
{
	private ArrayList<Listener> listeners = new ArrayList<>();

	public abstract void setValue(T value);
	public abstract T getValue();

	public final void alertListeners()
	{
		if(listeners != null)
		{
			for(Listener listener : listeners)
			{
				listener.alert();
			}
		}
	}

	public final void alertListeners(Listener except)
	{
		if(listeners != null)
		{
			for(Listener listener : listeners)
			{
				if(listener != except)
					listener.alert();
			}
		}
	}

	public final <U> void processAndAlert(Process<U> process, U value)
	{
		process.process(value);
		alertListeners();
	}

	public final void set(T value)
	{
		setValue(value);
		alertListeners();
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
		Listener listener = new Listener()
		{
			@Override
			public void alert()
			{
				other.setValue(converter.convert(getValue()));
				//other.alertListeners(this);
			}
		};
		addListener(listener);
	}

	public <U> void bindBidirectional(Property<U> other, Converter<T, U> converter, Converter<U, T> converterBack)
	{
		bind(other, converter);
		other.bind(this, converterBack);
	}

	public void bindBidirectional(Property<T> other, Converter<T, T> converter)
	{
		bindBidirectional(other, converter, converter);
	}

	@Override
	public String toString()
	{
		return getValue().toString();
	}

	public static <T> Property<T> generate(Field field, Object object)
	{
		return new Property<T>()
		{
			@Override
			public void setValue(T value)
			{
				try
				{
					field.set(object, value);
				} catch (IllegalAccessException e)
				{
					e.printStackTrace();
				}
			}

			@Override
			public T getValue()
			{
				try
				{
					return (T) field.get(object);
				} catch (IllegalAccessException e)
				{
					e.printStackTrace();
				}
				return null;
			}
		};
	}
}
