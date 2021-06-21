package com.elemengine.event.handler;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class CursorPosCallback extends GLFWCursorPosCallback
{
	private CursorPosEventHandler handler;

	public CursorPosCallback(CursorPosEventHandler handler)
	{
		this.handler = handler;
	}

	@Override
	public void invoke(long window, double xpos, double ypos)
	{
		handler.cursorMovedEventHandler(xpos, ypos);
	}
}
