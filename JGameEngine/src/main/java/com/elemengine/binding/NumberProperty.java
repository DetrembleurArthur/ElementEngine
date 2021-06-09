package com.elemengine.binding;

public abstract class NumberProperty<T extends Number> extends Property<T>
{
	public abstract void bindAddition(Property<T> property, T factor);
	public abstract void bindSubstraction(Property<T> property, T factor);
	public abstract void bindMultiplication(Property<T> property, T factor);
	public abstract void bindDivision(Property<T> property, T factor);
}
