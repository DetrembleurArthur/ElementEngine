package game.jgengine.graphics.gui.widgets;

import game.jgengine.graphics.rendering.Texture;
import game.jgengine.graphics.shapes.Circle;

public class WCircle extends Widget<Circle>
{
	protected WCircle(float radius, int points, Texture texture)
	{
		super(new Circle(radius, points, texture));
	}
}
