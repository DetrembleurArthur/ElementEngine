package game.jgengine.graphics.gui.event;

import game.jgengine.entity.GameObject;
import game.jgengine.entity.Updateable;

import java.util.ArrayList;

public abstract class EventManager implements Updateable
{
	private ArrayList<Event> events;

	public EventManager()
	{
		events = new ArrayList<>();
	}

	public ArrayList<Event> getEvents()
	{
		return events;
	}

	public void sortEvents()
	{
		events.sort(Event::compareTo);
	}

	public void setEventPriority(Class<? extends Event> eventClass, int priority)
	{
		getEvent(eventClass).setPriority(priority);
		sortEvents();
	}

	public void addEvent(Event event)
	{
		events.add(event);
		sortEvents();
	}

	public <T extends Event> T getEvent(Class<T> eventClass)
	{
		for(Event event : events)
		{
			if(event.getClass().equals(eventClass))
			{
				return (T)event;
			}
		}
		return null;
	}

	protected void pollEvent()
	{
		for(Event event : events)
		{
			if(event.isAppend())
			{
				event.run(this);
			}
		}
	}

	@Override
	public void update()
	{
		pollEvent();
	}

	protected boolean onEvent(Class<? extends Event> eventClass, ActionEvent action)
	{
		for(Event event : getEvents())
		{
			if(event.getClass().equals(eventClass))
			{
				event.addActionEvent(action);
				return true;
			}
		}
		return false;
	}
}