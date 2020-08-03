package game.jgengine.graphics.gui;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.gui.event.*;
import game.jgengine.graphics.shapes.Shape;
import game.jgengine.sys.Game;

public class Widget<T extends Shape> extends EventManager
{
	private final T shape;

	protected Widget(T shape)
	{
		this.shape = shape;
	}

	public T getShape()
	{
		return shape;
	}

	public void onMouseHovering(ActionEvent action)
	{
		if(!onEvent(MouseHoveringEvent.class, action))
		{
			getEvents().add(new MouseHoveringEvent(getShape()).addActionEvent(action));
		}
	}

	public void onMouseEntered(ActionEvent action)
	{
		if(!onEvent(MouseEnteredEvent.class, action))
		{
			getEvents().add(new MouseEnteredEvent(getShape()).addActionEvent(action));
		}
	}

	public void onMouseExited(ActionEvent action)
	{
		if(!onEvent(MouseExitedEvent.class, action))
		{
			getEvents().add(new MouseExitedEvent(getShape()).addActionEvent(action));
		}
	}

	public void onMouseMoved(ActionEvent action)
	{
		if(!onEvent(MouseMoveEvent.class, action))
		{
			getEvents().add(new MouseMoveEvent(getShape()).addActionEvent(action));
		}
	}

	public void onMouseButtonClicked(ActionEvent action, int buttonId, boolean repeated)
	{
		if(!onMouseButtonEvent(buttonId, action))
		{
			getEvents().add(new MouseButtonClickEvent(getShape(), buttonId, repeated).addActionEvent(action));
		}
	}

	public void onMouseLeftButtonClicked(ActionEvent action, boolean repeated)
	{
		if(!onEvent(MouseLeftButtonClickEvent.class, action))
		{
			getEvents().add(new MouseLeftButtonClickEvent(getShape(), repeated).addActionEvent(action));
		}
	}

	public void onMouseRightButtonClicked(ActionEvent action, boolean repeated)
	{
		if(!onEvent(MouseRightButtonClickEvent.class, action))
		{
			getEvents().add(new MouseRightButtonClickEvent(getShape(), repeated).addActionEvent(action));
		}
	}

	public void onMouseMiddleButtonClicked(ActionEvent action, boolean repeated)
	{
		if(!onEvent(MouseMiddleButtonClickEvent.class, action))
		{
			getEvents().add(new MouseMiddleButtonClickEvent(getShape(), repeated).addActionEvent(action));
		}
	}
}
