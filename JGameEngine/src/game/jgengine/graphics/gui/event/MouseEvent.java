package game.jgengine.graphics.gui.event;

import game.jgengine.entity.GameObject;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.shapes.Shape;
import game.jgengine.sys.Game;
import game.jgengine.sys.Scene2D;

public abstract class MouseEvent extends Event
{
	protected final Camera2D camera;
	protected final Shape relativeObject;

	public MouseEvent(Shape relativeObject)
	{
		this.camera = ((Scene2D)Game.GAME.getCurrentScene()).getCamera2d();
		this.relativeObject = relativeObject;
	}

	public Camera2D getCamera()
	{
		return camera;
	}

	public Shape getRelativeObject()
	{
		return relativeObject;
	}
}
