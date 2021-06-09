package com.elemengine.utils;

import static org.lwjgl.glfw.GLFW.glfwGetClipboardString;
import static org.lwjgl.glfw.GLFW.glfwSetClipboardString;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Clipboard
{
	public static String get()
	{
		return glfwGetClipboardString(NULL);
	}

	public static void set(String buffer)
	{
		glfwSetClipboardString(NULL, buffer);
	}
}
