package game.jgengine.graphics.gui.event;

import game.jgengine.event.Mouse;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.shapes.Shape;
import game.jgengine.sys.Game;

public class MouseHoveringEvent extends MouseEvent
{
	public MouseHoveringEvent(Shape relativeObject)
	{
		super(relativeObject);
	}

	@Override
	boolean isAppend()
	{
		return relativeObject.contains(Mouse.getPosition(camera));
	}
}
