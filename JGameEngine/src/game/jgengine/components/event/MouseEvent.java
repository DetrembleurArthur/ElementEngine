package game.jgengine.components.event;

import game.jgengine.entity.GameObject;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.sys.Game;
import game.jgengine.sys.Scene2D;

public abstract class MouseEvent extends RelativeEvent
{
	protected final Camera2D camera;

	public MouseEvent(GameObject relativeObject)
	{
		super(relativeObject);
		this.camera = ((Scene2D)Game.GAME.getCurrentScene()).getCamera2d();
		this.object = relativeObject;
	}

	public Camera2D getCamera()
	{
		return camera;
	}
}
