package game.jgengine.graphics.gui.event;

import game.jgengine.debug.Logs;

import java.util.ArrayList;

public abstract class EventManager
{
	private ArrayList<Event> events;
	private ArrayList<Event> callLaterEvents = new ArrayList<>();

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
				callLaterEvents.add(event);
			}
		}
		if(!callLaterEvents.isEmpty())
		{
			for (Event event : callLaterEvents)
			{
				Logs.print("EVENT => " + event.getClass().getSimpleName() + " FROM " + this.getClass().getSimpleName());
				event.run(this);
			}
			callLaterEvents.clear();
		}
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

	public void clearEvents()
	{
		events.clear();
	}

	public void clearEvent(Class<? extends Event> eventClass)
	{
		Event event = getEvent(eventClass);
		if(event != null)
			events.remove(event);
	}
}
