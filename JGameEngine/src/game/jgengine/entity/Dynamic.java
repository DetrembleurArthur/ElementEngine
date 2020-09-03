package game.jgengine.entity;

import game.jgengine.graphics.rendering.Renderer;

public interface Dynamic extends Runnable, Drawable, Destroyable
{
	@Override
	default void run() {}
}
