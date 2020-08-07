package game.jgengine.graphics.gui.event;

import game.jgengine.debug.Logs;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.shapes.Shape;
import org.joml.Vector2f;

public class MouseButtonDragEvent extends MouseLeftButtonClickEvent
{
	public static class Orientation
	{
		public final static int BOTH = 0;
		public final static int HORIZONTAL = 1;
		public final static int VERTICAL = 2;
	}

	private Vector2f relativeVector;

	public MouseButtonDragEvent(Shape relativeObject)
	{
		super(relativeObject, true);
		relativeVector = new Vector2f();
	}

	@Override
	boolean isAppend()
	{
		boolean clicked = isClicked();
		boolean ret = super.isAppend();
		if(ret)
		{
			var mousePosition = Mouse.getPosition(camera);
			if(!clicked)
			{
				relativeVector = mousePosition.sub(relativeObject.getPosition2D());
			}
			else
			{
				return true;
			}
			return true;
		}
		return false;
	}

	public Vector2f getRelativeVector()
	{
		return relativeVector;
	}

	public void setDragActionEvent(int orientation)
	{
		switch (orientation)
		{
			case Orientation.BOTH:
				setBothDragActionEvent();
				break;

			case Orientation.HORIZONTAL:
				setHorizontalDragActionEvent();
				break;

			case Orientation.VERTICAL:
				setVerticalDragActionEvent();
				break;
		}
	}

	public void setBothDragActionEvent()
	{
		addActionEvent(sender -> relativeObject.setPosition(Mouse.getPosition(camera).sub(relativeVector)));
	}

	public void setHorizontalDragActionEvent()
	{
		addActionEvent(sender -> relativeObject.setPosition(new Vector2f(Mouse.getPosition(camera).sub(new Vector2f(relativeVector.x, 0)).x, relativeObject.getPosition2D().y)));
	}

	public void setVerticalDragActionEvent()
	{
		addActionEvent(sender -> relativeObject.setPosition(new Vector2f(relativeObject.getPosition2D().x, Mouse.getPosition(camera).sub(new Vector2f(0, relativeVector.y)).y)));
	}
}
