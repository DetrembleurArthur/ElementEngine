package game.jgengine.graphics.gui.event;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.shapes.Shape;
import org.joml.Vector2f;

public class MouseButtonDragEvent extends MouseLeftButtonClickEvent
{
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

	public ActionEvent generateDragActionEvent()
	{
		return sender -> relativeObject.setPosition(Mouse.getPosition(camera).sub(relativeVector));
	}
}
