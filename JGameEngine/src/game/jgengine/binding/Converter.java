package game.jgengine.binding;

public interface Converter<T, U>
{
	U convert(T value);
}
