package game.jgengine.utils;

import org.joml.Vector2f;

public class MathUtil
{
	public static Vector2f rotateAround(Vector2f rotable, Vector2f rotPoint, float angleDegree)
	{
		var pos = new Vector2f(rotable);
		pos.x -= rotPoint.x;
		pos.y -= rotPoint.y;
		var angle = Math.toRadians(angleDegree);
		var posx = (float) (Math.cos(angle) * pos.x - Math.sin(angle) * pos.y);
		var posy = (float) (Math.sin(angle) * pos.x + Math.cos(angle) * pos.y);
		pos.x = posx + rotPoint.x;
		pos.y = posy + rotPoint.y;
		return pos;
	}

	public static Vector2f rotateAround(Vector2f rotable, Vector2f rotPoint, Vector2f angleDegree)
	{
		var pos = new Vector2f(rotable);
		pos.x -= rotPoint.x;
		pos.y -= rotPoint.y;
		var angle = new Vector2f((float)Math.toRadians(angleDegree.x), (float)Math.toRadians(angleDegree.y));
		var posx = (float) (Math.cos(angle.x) * pos.x - Math.sin(angle.x) * pos.y);
		var posy = (float) (Math.sin(angle.y) * pos.x + Math.cos(angle.y) * pos.y);
		pos.x = posx + rotPoint.x;
		pos.y = posy + rotPoint.y;
		return pos;
	}

	public static boolean boxContains(Vector2f boxPosition, Vector2f boxSize, Vector2f point)
	{
		return point.x >= boxPosition.x && point.x <= boxPosition.x + boxSize.x && point.y >= boxPosition.y && point.y <= boxPosition.y + boxSize.y;
	}

	public static Vector2f getPoint(Vector2f center, float radius, float angleDegree)
	{
		return new Vector2f(
				center.x + radius * (float)Math.sin(Math.toRadians(angleDegree)),
				center.y + -radius * (float)Math.cos(Math.toRadians(angleDegree)));
	}
}
