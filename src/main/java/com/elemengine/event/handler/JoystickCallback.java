package com.elemengine.event.handler;

import org.lwjgl.glfw.GLFWJoystickCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import static org.lwjgl.glfw.GLFW.*;

public class JoystickCallback extends GLFWJoystickCallback
{
	private JoystickEventHandler handler;

	public JoystickCallback(JoystickEventHandler handler)
	{
		this.handler = handler;
	}

	@Override
	public void invoke(int jid, int event)
	{
		if(event == GLFW_CONNECTED)
		{
			handler.joystickConnectedEventHandler(jid, event);
		}
		else
		{
			handler.joystickDisconnectedEventHandler(jid, event);
		}
	}
}
