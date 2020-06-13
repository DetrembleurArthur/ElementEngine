package game.jgengine.event;

import game.jgengine.graphics.camera.Camera2D;
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

	public static Vector2f getPosition(Window window, Camera2D camera)
	{
		var pos = getPosition(window);
		return pos.add(camera.getPosition());
	}

	public static Vector2f getPosition()
	{
		if(Window.currentWindow != null)
		{
			return getPosition(Window.currentWindow);
		}
		return null;
	}

	public static Vector2f getPosition(Camera2D camera)
	{
		if(Window.currentWindow != null)
		{
			return getPosition(Window.currentWindow, camera);
		}
		return null;
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

	public static void setPosition(double x, double y)
	{
		if(Window.currentWindow != null)
		{
			setPosition(Window.currentWindow, x, y);
		}
	}

	public static void setPosition(Vector2f pos)
	{
		if(Window.currentWindow != null)
		{
			setPosition(Window.currentWindow, pos);
		}
	}
}
