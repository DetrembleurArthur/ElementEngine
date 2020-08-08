package game.jgengine.graphics.gui.widgets;

import game.jgengine.graphics.rendering.Texture;
import game.jgengine.graphics.shapes.Rectangle;

public class WRectangle extends Widget<Rectangle>
{
	public WRectangle(Texture texture)
	{
		super(new Rectangle(texture));
	}
}
