package game.jgengine.components.event;

import game.jgengine.entity.GameObject;
import game.jgengine.event.Mouse;

public class MouseRightButtonClickEvent extends MouseButtonClickEvent
{
	public MouseRightButtonClickEvent(GameObject relativeObject, boolean repeated)
	{
		super(relativeObject, Mouse.Button.RIGHT, repeated);
	}
}
