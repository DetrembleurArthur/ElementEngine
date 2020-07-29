package game.jgengine.binding;

import javax.lang.model.type.PrimitiveType;

public interface Listener
{
	void alert();

	static <T, U> Listener multiplyIntField(int n, String fieldName, NotifyProperty<T> notified, NotifyProperty<U> notifier)
	{
		return () -> {
			notified.setFieldByName(fieldName, (int)notifier.getFieldByName(fieldName) * n);
		};
	}

	static <T, U> Listener multiplyFloatField(int n, String fieldName, NotifyProperty<T> notified, NotifyProperty<U> notifier)
	{
		return () -> {
			notified.setFieldByName(fieldName, (float)notifier.getFieldByName(fieldName) * n);
		};
	}
}
