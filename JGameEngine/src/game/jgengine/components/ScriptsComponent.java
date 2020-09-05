package game.jgengine.components;

import game.jgengine.entity.GameObject;
import game.jgengine.scripting.Script;

import java.util.ArrayList;

public class ScriptsComponent extends Component implements Runnable
{
	private ArrayList<Script> scripts;

	public ScriptsComponent(GameObject relativeObject)
	{
		super(relativeObject);
		scripts = new ArrayList<>();
	}

	public ScriptsComponent add(Script script)
	{
		scripts.add(script);
		return this;
	}

	@Override
	public void run()
	{
		for(Script script : scripts)
		{
			script.run();
		}
	}
}
