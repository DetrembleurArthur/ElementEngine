package game.jgengine.graphics.gui.widgets;

import game.jgengine.graphics.rendering.Texture;
import game.jgengine.graphics.shapes.Triangle;
import game.jgengine.graphics.texts.Text;

public class WTriangle extends Widget<Triangle>
{
	public WTriangle(Texture texture)
	{
		super(new Triangle(texture));
	}
}
