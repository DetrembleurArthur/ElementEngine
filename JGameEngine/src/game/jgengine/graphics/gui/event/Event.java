package game.jgengine.graphics.gui.event;

import game.jgengine.graphics.gui.Widget;

import java.util.ArrayList;

public abstract class Event
{
	private ArrayList<ActionEvent> actions;

	public Event()
	{
		actions = new ArrayList<>();
	}

	abstract boolean isAppend();
	void run(EventManager sender)
	{
		for(ActionEvent action : actions)
		{
			action.action(sender);
		}
	}

	public Event addActionEvent(ActionEvent action)
	{
		actions.add(action);
		return this;
	}
}
