package com.elemengine.scripting;

public class CScript extends Script
{
	private Condition condition;

	public CScript()
	{
		super();
		condition = null;
	}

	public CScript setCondition(Condition condition)
	{
		this.condition = condition;
		return this;
	}

	@Override
	public void run()
	{
		if(condition != null)
		{
			if(condition.isTrue())
			{
				super.run();
			}
		}
	}
}
