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
			addEvent(new MouseHoveringEvent(getShape()).addActionEvent(action));
		}
	}

	public void onMouseEntered(ActionEvent action)
	{
		if(!onEvent(MouseEnteredEvent.class, action))
		{
			addEvent(new MouseEnteredEvent(getShape()).addActionEvent(action));
		}
	}

	public void onMouseExited(ActionEvent action)
	{
		if(!onEvent(MouseExitedEvent.class, action))
		{
			addEvent(new MouseExitedEvent(getShape()).addActionEvent(action));
		}
	}

	public void onMouseMoved(ActionEvent action)
	{
		if(!onEvent(MouseMoveEvent.class, action))
		{
			addEvent(new MouseMoveEvent(getShape()).addActionEvent(action));
		}
	}

	public void onMouseButtonClicked(ActionEvent action, boolean repeated)
	{
		if(!onEvent(MouseButtonClickEvent.class, action))
		{
			addEvent(new MouseButtonClickEvent(getShape(), repeated).addActionEvent(action));
		}
	}

	public void onMouseLeftButtonClicked(ActionEvent action, boolean repeated)
	{
		if(!onEvent(MouseLeftButtonClickEvent.class, action))
		{
			addEvent(new MouseLeftButtonClickEvent(getShape(), repeated).addActionEvent(action));
		}
	}

	public void onMouseRightButtonClicked(ActionEvent action, boolean repeated)
	{
		if(!onEvent(MouseRightButtonClickEvent.class, action))
		{
			addEvent(new MouseRightButtonClickEvent(getShape(), repeated).addActionEvent(action));
		}
	}

	public void onMouseMiddleButtonClicked(ActionEvent action, boolean repeated)
	{
		if(!onEvent(MouseMiddleButtonClickEvent.class, action))
		{
			addEvent(new MouseMiddleButtonClickEvent(getShape(), repeated).addActionEvent(action));
		}
	}

	public void onMouseButtonReleased(ActionEvent action)
	{
		if(!onEvent(MouseButtonReleasedEvent.class, action))
		{
			addEvent(new MouseButtonReleasedEvent(getShape()).addActionEvent(action));
		}
	}

	public void onMouseLeftButtonReleased(ActionEvent action)
	{
		if(!onEvent(MouseLeftButtonRealeasedEvent.class, action))
		{
			addEvent(new MouseLeftButtonRealeasedEvent(getShape()).addActionEvent(action));
		}
	}

	public void onMouseRightButtonReleased(ActionEvent action)
	{
		if(!onEvent(MouseRightButtonRealeasedEvent.class, action))
		{
			addEvent(new MouseRightButtonRealeasedEvent(getShape()).addActionEvent(action));
		}
	}

	public void onMouseMiddleButtonReleased(ActionEvent action)
	{
		if(!onEvent(MouseMiddleButtonRealeasedEvent.class, action))
		{
			addEvent(new MouseMiddleButtonRealeasedEvent(getShape()).addActionEvent(action));
		}
	}
}
