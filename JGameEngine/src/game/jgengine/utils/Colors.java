package game.jgengine.utils;

import java.util.Random;

public class Colors
{
	public static final Color RED = new Color(255, 0, 0);
	public static final Color GREEN = new Color(0, 255, 0);
	public static final Color BLUE = new Color(0, 0, 255);
	public static final Color TRANSPARENT = new Color(0, 0, 0, 0);
	public static final Color BLACK = new Color(0, 0, 0);
	public static final Color WHITE = new Color(255, 255, 255);
	public static final Color CYAN = new Color(0, 255, 255);
	public static final Color YELLOW = new Color(255, 255, 0);
	public static final Color MAGENTA = new Color(255, 0, 255);
	public static final Color ORANGE = new Color(255, 128, 0);
	public static final Color PINK = new Color(255, 0, 128);
	public static final Color TURQUOISE = new Color(0, 255, 200);
	public static final Color LIME = new Color(150, 255, 0);
	public static final Color PURPLE = new Color(200, 0, 255);

	public static Color random()
	{
		Random rand = new Random();
		return new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
	}
}
