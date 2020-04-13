package game.jgengine.event;

import game.jgengine.sys.Window;
import game.jgengine.utils.DPoint2D;
import game.jgengine.utils.IPoint2D;

import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPos;

public class Mouse
{
	public static DPoint2D getPosition(Window window)
	{
		double[] x = new double[1], y = new double[1];
		glfwGetCursorPos(window.getId(), x, y);
		return new DPoint2D(x[0], y[0]);
	}

	public static IPoint2D getIPosition(Window window)
	{
		double[] x = new double[1], y = new double[1];
		glfwGetCursorPos(window.getId(), x, y);
		return new IPoint2D((int)x[0], (int)y[0]);
	}

	public static  void setPosition(Window window, double x, double y)
	{
		glfwSetCursorPos(window.getId(), x, y);
	}

	public static  void setPosition(Window window, DPoint2D pos)
	{
		glfwSetCursorPos(window.getId(), pos.getX(), pos.getY());
	}
}
