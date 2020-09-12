package game.jgengine.components.event;

import game.jgengine.components.Component;
import game.jgengine.debug.Logs;
import game.jgengine.entity.GameObject;

import java.util.ArrayList;

public class EventManagerComponent extends Component implements Runnable
{
	private ArrayList<Event> events;
	private ArrayList<Event> callLaterEvents = new ArrayList<>();

	public EventManagerComponent(GameObject relativeObject)
	{
		super(relativeObject);
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
				Logs.print("EVENT => " + event.getClass().getSimpleName());
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


	public void onMouseHovering(ActionEvent action)
	{
		if(!onEvent(MouseHoveringEvent.class, action))
		{
			addEvent(new MouseHoveringEvent(getRelativeObject()).addActionEvent(action));
		}
	}

	public void onMouseEntered(ActionEvent action)
	{
		if(!onEvent(MouseEnteredEvent.class, action))
		{
			addEvent(new MouseEnteredEvent(getRelativeObject()).addActionEvent(action));
		}
	}

	public void onMouseExited(ActionEvent action)
	{
		if(!onEvent(MouseExitedEvent.class, action))
		{
			addEvent(new MouseExitedEvent(getRelativeObject()).addActionEvent(action));
		}
	}

	public void onMouseMoved(ActionEvent action)
	{
		if(!onEvent(MouseMoveEvent.class, action))
		{
			addEvent(new MouseMoveEvent(getRelativeObject()).addActionEvent(action));
		}
	}

	public void onMouseButtonClicked(ActionEvent action, boolean repeated)
	{
		if(!onEvent(MouseButtonClickEvent.class, action))
		{
			addEvent(new MouseButtonClickEvent(getRelativeObject(), repeated).addActionEvent(action));
		}
	}

	public void onMouseLeftButtonClicked(ActionEvent action, boolean repeated)
	{
		if(!onEvent(MouseLeftButtonClickEvent.class, action))
		{
			addEvent(new MouseLeftButtonClickEvent(getRelativeObject(), repeated).addActionEvent(action));
		}
	}

	public void onMouseRightButtonClicked(ActionEvent action, boolean repeated)
	{
		if(!onEvent(MouseRightButtonClickEvent.class, action))
		{
			addEvent(new MouseRightButtonClickEvent(getRelativeObject(), repeated).addActionEvent(action));
		}
	}

	public void onMouseMiddleButtonClicked(ActionEvent action, boolean repeated)
	{
		if(!onEvent(MouseMiddleButtonClickEvent.class, action))
		{
			addEvent(new MouseMiddleButtonClickEvent(getRelativeObject(), repeated).addActionEvent(action));
		}
	}

	public void onMouseButtonReleased(ActionEvent action)
	{
		if(!onEvent(MouseButtonReleasedEvent.class, action))
		{
			addEvent(new MouseButtonReleasedEvent(getRelativeObject()).addActionEvent(action));
		}
	}

	public void onMouseLeftButtonReleased(ActionEvent action)
	{
		if(!onEvent(MouseLeftButtonRealeasedEvent.class, action))
		{
			addEvent(new MouseLeftButtonRealeasedEvent(getRelativeObject()).addActionEvent(action));
		}
	}

	public void onMouseRightButtonReleased(ActionEvent action)
	{
		if(!onEvent(MouseRightButtonRealeasedEvent.class, action))
		{
			addEvent(new MouseRightButtonRealeasedEvent(getRelativeObject()).addActionEvent(action));
		}
	}

	public void onMouseMiddleButtonReleased(ActionEvent action)
	{
		if(!onEvent(MouseMiddleButtonRealeasedEvent.class, action))
		{
			addEvent(new MouseMiddleButtonRealeasedEvent(getRelativeObject()).addActionEvent(action));
		}
	}

	public void onMouseButtonDraged(ActionEvent action)
	{
		if(!onEvent(MouseButtonDragEvent.class, action))
		{
			addEvent(new MouseButtonDragEvent(getRelativeObject()).addActionEvent(action));
		}
	}

	private void enableMouseDragging(int orientation)
	{
		MouseButtonDragEvent event = getEvent(MouseButtonDragEvent.class);
		if(event != null)
		{
			event.setDragActionEvent(orientation);
		}
		else
		{
			event = new MouseButtonDragEvent(getRelativeObject());
			event.setDragActionEvent(orientation);
			addEvent(event);
		}
	}

	public void enableMouseDragging()
	{
		enableMouseDragging(MouseButtonDragEvent.Orientation.BOTH);
	}

	public void enableHorizontalMouseDragging()
	{
		enableMouseDragging(MouseButtonDragEvent.Orientation.HORIZONTAL);
	}

	public void enableVerticalMouseDragging()
	{
		enableMouseDragging(MouseButtonDragEvent.Orientation.VERTICAL);
	}

	public void changeDraggingOrientation(int orientation)
	{
		clearEvent(MouseButtonDragEvent.class);
		enableMouseDragging(orientation);
	}

	public void onPositionChanged(ActionEvent action)
	{
		if(!onEvent(PositionChangedEvent.class, action))
		{
			addEvent(new PositionChangedEvent(getRelativeObject()).addActionEvent(action));
		}
	}

	public void onMouseButtonDoubleClicked(ActionEvent action)
	{
		if(!onEvent(MouseButtonDoubleClickEvent.class, action))
		{
			addEvent(new MouseButtonDoubleClickEvent(getRelativeObject()).addActionEvent(action));
		}
	}

	public void onFillColorChanged(ActionEvent action)
	{
		if(!onEvent(FillColorChangedEvent.class, action))
		{
			addEvent(new FillColorChangedEvent(getRelativeObject()).addActionEvent(action));
		}
	}

	public void onValueChanged(ActionEvent action)
	{
		Valuable valuable = getRelativeObject() instanceof Valuable ? (Valuable)getRelativeObject() : null;
		if(valuable != null)
		{
			if(!onEvent(ValueChangedEvent.class, action))
			{
				addEvent(new ValueChangedEvent(valuable, valuable.getValue()).addActionEvent(action));
			}
		}
	}

	@Override
	public void run()
	{
		pollEvent();
	}
}
