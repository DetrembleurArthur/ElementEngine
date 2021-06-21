package com.elemengine.binding;

public abstract class IntegerProperty extends NumberProperty<Integer>
{
	@Override
	public void bindAddition(Property<Integer> property, Integer factor)
	{
		addListener(() -> property.setValue(getValue() + factor));
	}

	@Override
	public void bindMultiplication(Property<Integer> property, Integer factor)
	{
		addListener(() -> property.setValue(getValue() * factor));
	}

	@Override
	public void bindSubstraction(Property<Integer> property, Integer factor)
	{
		addListener(() -> property.setValue(getValue() - factor));
	}

	@Override
	public void bindDivision(Property<Integer> property, Integer factor)
	{
		addListener(() -> property.setValue(getValue() / factor));
	}
}
