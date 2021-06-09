package com.elemengine.time;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class Time
{
	public static double timeStarted = glfwGetTime();
	public static double getTime()
	{
		return glfwGetTime();
	}
	public static double getElapsedTime()
	{
		return glfwGetTime() - timeStarted;
	}
}
