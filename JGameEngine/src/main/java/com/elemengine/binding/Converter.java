package com.elemengine.binding;

public interface Converter<T, U>
{
	U convert(T value);
}
