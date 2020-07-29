package game.jgengine.binding;

import game.jgengine.debug.Logs;

import javax.lang.model.type.PrimitiveType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Property<T>
{
	private T value;

	public Property(T value)
	{
		set(value);
	}

	public T get()
	{
		return value;
	}

	public void set(T value)
	{
		setValue(value);
	}

	public <U> void setFieldBySetter(String setter, U object)
	{
		try
		{
			Class oclass = null;
			if(object instanceof Byte) oclass = byte.class;
			else if(object instanceof Short) oclass = short.class;
			else if(object instanceof Integer) oclass = int.class;
			else if(object instanceof Long) oclass = long.class;
			else if(object instanceof Float) oclass = float.class;
			else if(object instanceof Double) oclass = double.class;
			else if(object instanceof Character) oclass = char.class;
			else oclass = object.getClass();
			value.getClass().getMethod(setter, oclass).invoke(value, object);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
	}

	public <U> void setFieldValueByName(String field, U object)
	{
		setFieldBySetter("set" + field.substring(0, 1).toUpperCase() + field.substring(1), object);
	}

	public <U> void setFieldByName(String field, U object)
	{
		setFieldValueByName(field, object);
	}

	public boolean accessibleField(String fieldName)
	{
		try
		{
			value.getClass().getField(fieldName);
		} catch (NoSuchFieldException e)
		{
			try
			{
				var returnType = value.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)).getReturnType();
				value.getClass().getMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), returnType);
			} catch (NoSuchMethodException ex)
			{
				return false;
			}
		}
		return true;
	}

	public Object getFieldByGetter(String getter)
	{
		try
		{
			return value.getClass().getMethod(getter).invoke(value);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public Object getFieldByName(String field)
	{
		return getFieldByGetter("get" + field.substring(0, 1).toUpperCase() + field.substring(1));
	}

	public void setValue(T value)
	{
		this.value = value;
	}

	public void bind(NotifyProperty<T> property)
	{
		property.addListener(() -> {setValue(property.get());});
	}

	public <U> void bindField(String field, NotifyProperty<U> property)
	{
		bindField(field, property, field);
	}

	public <U> void bindField(String thisField, NotifyProperty<U> property, String otherField)
	{
		property.addFieldListener(otherField, () -> {
			setFieldValueByName(thisField, property.getFieldByName(otherField));
		});
	}

	public <U> void bindIntField(String thisField, NotifyProperty<U> property, String otherField, IntOperation operation)
	{
		property.addFieldListener(otherField, () -> {
			setFieldValueByName(thisField, operation.operation((Integer)property.getFieldByName(otherField)));
		});
	}
}
