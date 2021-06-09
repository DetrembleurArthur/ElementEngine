package com.elemengine.components.scripts;

import com.elemengine.components.Component;
import com.elemengine.entity.GameObject;
import com.elemengine.scripting.Script;

import java.util.ArrayList;

public class ScriptsComponent extends Component implements Runnable
{
	private ArrayList<Script> CScripts;

	public ScriptsComponent(GameObject relativeObject)
	{
		super(relativeObject);
		CScripts = new ArrayList<>();
	}

	public ScriptsComponent add(Script CScript)
	{
		CScripts.add(CScript);
		return this;
	}

	@Override
	public void run()
	{
		for(Script CScript : CScripts)
		{
			CScript.run();
		}
	}
}
