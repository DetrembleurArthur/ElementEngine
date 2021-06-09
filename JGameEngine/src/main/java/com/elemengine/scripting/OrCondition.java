package com.elemengine.scripting;

public class OrCondition extends ComposedCondition
{
	@Override
	public boolean isTrue()
	{
		for(Condition condition : conditions)
		{
			if(condition.isTrue())
			{
				return true;
			}
		}
		return false;
	}
}
