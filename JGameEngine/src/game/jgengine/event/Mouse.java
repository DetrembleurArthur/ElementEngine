package game.jgengine.event;

import game.jgengine.sys.Window;
import game.jgengine.utils.Vec2f;
import game.jgengine.utils.Vec2i;

import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPos;

public class Mouse
{
	public static Vec2f getPosition(Window window)
	{
		double[] x = new double[1], y = new double[1];
		glfwGetCursorPos(window.getId(), x, y);
		return new Vec2f((float)x[0], (float)y[0]);
	}

	public static Vec2i getIPosition(Window window)
	{
		double[] x = new double[1], y = new double[1];
		glfwGetCursorPos(window.getId(), x, y);
		return new Vec2i((int)x[0], (int)y[0]);
	}

	public static  void setPosition(Window window, double x, double y)
	{
		glfwSetCursorPos(window.getId(), x, y);
	}

	public static  void setPosition(Window window, Vec2f pos)
	{
		glfwSetCursorPos(window.getId(), pos.getX(), pos.getY());
	}
}
