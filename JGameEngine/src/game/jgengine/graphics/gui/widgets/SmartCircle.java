package game.jgengine.graphics.gui.widgets;

import game.jgengine.graphics.rendering.Texture;
import game.jgengine.graphics.shapes.Circle;

public class SmartCircle extends SmartShape<Circle>
{
	public SmartCircle(float radius, int points, Texture texture)
	{
		super(new Circle(radius, points, texture));
	}
}
