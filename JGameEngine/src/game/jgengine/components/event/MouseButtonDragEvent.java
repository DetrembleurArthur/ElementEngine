package game.jgengine.components.event;

import game.jgengine.components.properties.CommonPropertiesComponent;
import game.jgengine.entity.GameObject;
import game.jgengine.event.Mouse;
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

	public MouseButtonDragEvent(GameObject relativeObject)
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
				relativeVector = mousePosition.sub(object.getPosition2D());
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
		CommonPropertiesComponent properties = object.getComponent(CommonPropertiesComponent.class);
		addActionEvent(properties != null ?
				sender -> properties.positionProperty.set(Mouse.getPosition(camera).sub(relativeVector)) :
				sender -> object.setPosition(Mouse.getPosition(camera).sub(relativeVector)));
	}

	public void setHorizontalDragActionEvent()
	{
		CommonPropertiesComponent properties = object.getComponent(CommonPropertiesComponent.class);
		addActionEvent(properties != null ?
				sender -> properties.xProperty.set(Mouse.getPosition(camera).x - relativeVector.x):
				sender -> object.setX(Mouse.getPosition(camera).x - relativeVector.x));
	}

	public void setVerticalDragActionEvent()
	{
		CommonPropertiesComponent properties = object.getComponent(CommonPropertiesComponent.class);
		addActionEvent(properties != null ?
				sender -> properties.yProperty.set(Mouse.getPosition(camera).y - relativeVector.y):
				sender -> object.setY(Mouse.getPosition(camera).y - relativeVector.y));
	}
}