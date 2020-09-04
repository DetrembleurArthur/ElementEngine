package game.jgengine.graphics.gui.widgets;

import game.jgengine.graphics.rendering.Texture;
import game.jgengine.graphics.shapes.Rectangle;

public class SmartRectangle extends SmartShape<Rectangle>
{
	public SmartRectangle(Texture texture)
	{
		super(new Rectangle(texture));
	}
}
