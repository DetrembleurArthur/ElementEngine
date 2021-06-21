package com.elemengine.event;

import com.elemengine.graphics.camera.Camera2D;
import com.elemengine.utils.MathUtil;
import com.elemengine.sys.Window;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class Mouse
{
	public static class Button
	{
		public static final int LEFT = GLFW_MOUSE_BUTTON_1;
		public static final int RIGHT = GLFW_MOUSE_BUTTON_2;
		public static final int MIDDLE = GLFW_MOUSE_BUTTON_3;
	}

	public static Vector2f getPosition()
	{
		double[] x = new double[1], y = new double[1];
		glfwGetCursorPos(Window.WINDOW.getId(), x, y);
		return new Vector2f(
				(float)x[0],
				(float)y[0]);
	}

	public static Vector2f getPosition(Camera2D camera)
	{
		return MathUtil.screenToWorld(getPosition(), camera);
	}


	public static Vector2f getIPosition()
	{
		double[] x = new double[1], y = new double[1];
		glfwGetCursorPos(Window.WINDOW.getId(), x, y);
		return new Vector2f((int)x[0], (int)y[0]);
	}

	public static  void setPosition(double x, double y)
	{
		glfwSetCursorPos(Window.WINDOW.getId(), x, y);
	}

	public static  void setPosition(Vector2f pos)
	{
		glfwSetCursorPos(Window.WINDOW.getId(), pos.x, pos.y);
	}

}
