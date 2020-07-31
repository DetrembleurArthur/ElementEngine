package game.jgengine.binding;

public abstract class FloatProperty extends NumberProperty<Float>
{
	@Override
	public void bindAddition(Property<Float> property, Float factor)
	{
		addListener(() -> property.setValue(getValue() + factor));
	}

	@Override
	public void bindMultiplication(Property<Float> property, Float factor)
	{
		addListener(() -> property.setValue(getValue() * factor));
	}

	@Override
	public void bindSubstraction(Property<Float> property, Float factor)
	{
		addListener(() -> property.setValue(getValue() - factor));
	}

	@Override
	public void bindDivision(Property<Float> property, Float factor)
	{
		addListener(() -> property.setValue(getValue() / factor));
	}
}
