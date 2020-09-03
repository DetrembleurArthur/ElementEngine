import game.jgengine.binding.*;
import game.jgengine.debug.Logs;
import game.jgengine.utils.MathUtil;
import org.lwjgl.system.linux.XDestroyWindowEvent;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class ConsoleTest
{
	public static void main(String[] args)
	{
		float[] v = new float[]
				{
						5, 7.5f, 10
				};

		MathUtil.normalize(v);
		for(float i : v) Logs.print(i);
	}
}
