package game.jgengine.event;

import game.jgengine.sys.Window;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPos;

public class Mouse
{
	public static Vector2f getPosition(Window window)
	{
		double[] x = new double[1], y = new double[1];
		glfwGetCursorPos(window.getId(), x, y);
		return new Vector2f((float)x[0], (float)y[0]);
	}

	public static Vector2f getIPosition(Window window)
	{
		double[] x = new double[1], y = new double[1];
		glfwGetCursorPos(window.getId(), x, y);
		return new Vector2f((int)x[0], (int)y[0]);
	}

	public static  void setPosition(Window window, double x, double y)
	{
		glfwSetCursorPos(window.getId(), x, y);
	}

	public static  void setPosition(Window window, Vector2f pos)
	{
		glfwSetCursorPos(window.getId(), pos.x, pos.y);
	}
}
