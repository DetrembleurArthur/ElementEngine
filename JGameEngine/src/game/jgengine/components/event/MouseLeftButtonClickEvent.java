package game.jgengine.components.event;

import game.jgengine.entity.GameObject;
import game.jgengine.event.Mouse;

public class MouseLeftButtonClickEvent extends MouseButtonClickEvent
{
	public MouseLeftButtonClickEvent(GameObject relativeObject, boolean repeated)
	{
		super(relativeObject, Mouse.Button.LEFT, repeated);
	}
}
