package game.jgengine.graphics.gui.widgets;

import game.jgengine.graphics.gui.event.*;
import game.jgengine.graphics.shapes.Shape;

public class Widget<T extends Shape> extends EventManager
{
	public static interface Configure<T extends Shape>
	{
		void configure(T shape);
	}

	private final T shape;

	protected Widget(T shape)
	{
		this.shape = shape;
	}

	public T getShape()
	{
		return shape;
	}

	public void with(Configure<T> configure)
	{
		configure.configure(shape);
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

	public void onMouseButtonDraged(ActionEvent action)
	{
		if(!onEvent(MouseButtonDragEvent.class, action))
		{
			addEvent(new MouseButtonDragEvent(getShape()).addActionEvent(action));
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
			event = new MouseButtonDragEvent(getShape());
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
			addEvent(new PositionChangedEvent(getShape()).addActionEvent(action));
		}
	}

	public void onMouseButtonDoubleClicked(ActionEvent action)
	{
		if(!onEvent(MouseButtonDoubleClickEvent.class, action))
		{
			addEvent(new MouseButtonDoubleClickEvent(getShape()).addActionEvent(action));
		}
	}

	public void onFillColorChanged(ActionEvent action)
	{
		if(!onEvent(FillColorChangedEvent.class, action))
		{
			addEvent(new FillColorChangedEvent(getShape()).addActionEvent(action));
		}
	}
}
