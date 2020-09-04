package game.jgengine.graphics.gui.widgets;

import game.jgengine.graphics.rendering.Texture;
import game.jgengine.graphics.shapes.Triangle;

public class SmartTriangle extends SmartShape<Triangle>
{
	public SmartTriangle(Texture texture)
	{
		super(new Triangle(texture));
	}
}
