package game.jgengine.components.scripts;

import game.jgengine.components.Component;
import game.jgengine.entity.GameObject;
import game.jgengine.scripting.CScript;
import game.jgengine.scripting.Script;

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
