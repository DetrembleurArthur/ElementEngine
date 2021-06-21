package com.elemengine.components.event;

import com.elemengine.components.Component;
import com.elemengine.debug.Log;
import com.elemengine.entity.GameObject;
import com.elemengine.graphics.camera.Camera2D;

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
				Log.print("EVENT => " + event.getClass().getSimpleName());
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


	public void onMouseHovering(ActionEvent action, Camera2D camera2D)
	{
		if(!onEvent(MouseHoveringEvent.class, action))
		{
			addEvent(new MouseHoveringEvent(getRelativeObject(), camera2D).addActionEvent(action));
		}
	}

	public void onMouseEntered(ActionEvent action, Camera2D camera2D)
	{
		if(!onEvent(MouseEnteredEvent.class, action))
		{
			addEvent(new MouseEnteredEvent(getRelativeObject(), camera2D).addActionEvent(action));
		}
	}

	public void onMouseExited(ActionEvent action, Camera2D camera2D)
	{
		if(!onEvent(MouseExitedEvent.class, action))
		{
			addEvent(new MouseExitedEvent(getRelativeObject(), camera2D).addActionEvent(action));
		}
	}

	public void onMouseMoved(ActionEvent action, Camera2D camera2D)
	{
		if(!onEvent(MouseMoveEvent.class, action))
		{
			addEvent(new MouseMoveEvent(getRelativeObject(), camera2D).addActionEvent(action));
		}
	}

	public void onMouseButtonClicked(ActionEvent action, boolean repeated, Camera2D camera2D)
	{
		if(!onEvent(MouseButtonClickEvent.class, action))
		{
			addEvent(new MouseButtonClickEvent(getRelativeObject(), repeated, camera2D).addActionEvent(action));
		}
	}

	public void onMouseLeftButtonClicked(ActionEvent action, boolean repeated, Camera2D camera2D)
	{
		if(!onEvent(MouseLeftButtonClickEvent.class, action))
		{
			addEvent(new MouseLeftButtonClickEvent(getRelativeObject(), repeated, camera2D).addActionEvent(action));
		}
	}

	public void onMouseRightButtonClicked(ActionEvent action, boolean repeated, Camera2D camera2D)
	{
		if(!onEvent(MouseRightButtonClickEvent.class, action))
		{
			addEvent(new MouseRightButtonClickEvent(getRelativeObject(), repeated, camera2D).addActionEvent(action));
		}
	}

	public void onMouseMiddleButtonClicked(ActionEvent action, boolean repeated, Camera2D camera2D)
	{
		if(!onEvent(MouseMiddleButtonClickEvent.class, action))
		{
			addEvent(new MouseMiddleButtonClickEvent(getRelativeObject(), repeated, camera2D).addActionEvent(action));
		}
	}

	public void onMouseButtonReleased(ActionEvent action, Camera2D camera2D)
	{
		if(!onEvent(MouseButtonReleasedEvent.class, action))
		{
			addEvent(new MouseButtonReleasedEvent(getRelativeObject(), camera2D).addActionEvent(action));
		}
	}

	public void onMouseLeftButtonReleased(ActionEvent action, Camera2D camera2D)
	{
		if(!onEvent(MouseLeftButtonRealeasedEvent.class, action))
		{
			addEvent(new MouseLeftButtonRealeasedEvent(getRelativeObject(), camera2D).addActionEvent(action));
		}
	}

	public void onMouseRightButtonReleased(ActionEvent action, Camera2D camera2D)
	{
		if(!onEvent(MouseRightButtonRealeasedEvent.class, action))
		{
			addEvent(new MouseRightButtonRealeasedEvent(getRelativeObject(), camera2D).addActionEvent(action));
		}
	}

	public void onMouseMiddleButtonReleased(ActionEvent action, Camera2D camera2D)
	{
		if(!onEvent(MouseMiddleButtonRealeasedEvent.class, action))
		{
			addEvent(new MouseMiddleButtonRealeasedEvent(getRelativeObject(), camera2D).addActionEvent(action));
		}
	}

	public void onMouseButtonDraged(ActionEvent action, Camera2D camera2D)
	{
		if(!onEvent(MouseButtonDragEvent.class, action))
		{
			addEvent(new MouseButtonDragEvent(getRelativeObject(), camera2D).addActionEvent(action));
		}
	}

	private void enableMouseDragging(int orientation, Camera2D camera2D)
	{
		MouseButtonDragEvent event = getEvent(MouseButtonDragEvent.class);
		if(event != null)
		{
			event.setDragActionEvent(orientation);
		}
		else
		{
			event = new MouseButtonDragEvent(getRelativeObject(), camera2D);
			event.setDragActionEvent(orientation);
			addEvent(event);
		}
	}

	public void enableMouseDragging(Camera2D camera2D)
	{
		enableMouseDragging(MouseButtonDragEvent.Orientation.BOTH, camera2D);
	}

	public void enableHorizontalMouseDragging(Camera2D camera2D)
	{
		enableMouseDragging(MouseButtonDragEvent.Orientation.HORIZONTAL, camera2D);
	}

	public void enableVerticalMouseDragging(Camera2D camera2D)
	{
		enableMouseDragging(MouseButtonDragEvent.Orientation.VERTICAL, camera2D);
	}

	public void changeDraggingOrientation(int orientation, Camera2D camera2D)
	{
		clearEvent(MouseButtonDragEvent.class);
		enableMouseDragging(orientation, camera2D);
	}

	public void onPositionChanged(ActionEvent action)
	{
		if(!onEvent(PositionChangedEvent.class, action))
		{
			addEvent(new PositionChangedEvent(getRelativeObject()).addActionEvent(action));
		}
	}

	public void onMouseButtonDoubleClicked(ActionEvent action, Camera2D camera2D)
	{
		if(!onEvent(MouseButtonDoubleClickEvent.class, action))
		{
			addEvent(new MouseButtonDoubleClickEvent(getRelativeObject(), camera2D).addActionEvent(action));
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
