package game.jgengine.event;

import game.jgengine.sys.Window;

import static org.lwjgl.glfw.GLFW.*;

public class Input
{
	public static boolean isKeyPressed(Window window, int keycode)
	{
		return glfwGetKey(window.getId(), keycode) == 1;
	}

	public static boolean isButtonPressed(Window window, int buttonCode)
	{
		return glfwGetMouseButton(window.getId(), buttonCode) == 1;
	}

	public static boolean isLeftButtonPressed(Window window)
	{
		return glfwGetMouseButton(window.getId(), GLFW_MOUSE_BUTTON_1) == 1;
	}

	public static boolean isRightButtonPressed(Window window)
	{
		return glfwGetMouseButton(window.getId(), GLFW_MOUSE_BUTTON_2) == 1;
	}

	public static boolean isMiddleButtonPressed(Window window)
	{
		return glfwGetMouseButton(window.getId(), GLFW_MOUSE_BUTTON_3) == 1;
	}
}
