package game.jgengine.graphics.gui.event;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.gui.widgets.SmartShape;
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

	public MouseButtonDragEvent(SmartShape<?> relativeObject)
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
				relativeVector = mousePosition.sub(object.getShape().getPosition2D());
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
		addActionEvent(sender -> object.positionProperty.set(Mouse.getPosition(camera).sub(relativeVector)));
	}

	public void setHorizontalDragActionEvent()
	{
		addActionEvent(sender -> object.xProperty.set(Mouse.getPosition(camera).x - relativeVector.x));
	}

	public void setVerticalDragActionEvent()
	{
		addActionEvent(sender -> object.yProperty.set(Mouse.getPosition(camera).y - relativeVector.y));
	}
}
