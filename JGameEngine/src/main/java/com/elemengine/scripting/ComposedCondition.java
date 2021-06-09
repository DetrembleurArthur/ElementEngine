package com.elemengine.scripting;

import java.util.ArrayList;

public abstract class ComposedCondition implements Condition
{
	protected ArrayList<Condition> conditions;

	public ComposedCondition()
	{
		conditions = new ArrayList<>();
	}

	public ComposedCondition addCondition(Condition condition)
	{
		conditions.add(condition);
		return this;
	}
}

