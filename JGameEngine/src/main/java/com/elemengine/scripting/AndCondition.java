package com.elemengine.scripting;

public class AndCondition extends ComposedCondition
{
	@Override
	public boolean isTrue()
	{
		for(Condition condition : conditions)
		{
			if(!condition.isTrue())
			{
				return false;
			}
		}
		return true;
	}
}
