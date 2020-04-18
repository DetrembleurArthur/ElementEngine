package game.jgengine.event.handler;

import org.lwjgl.glfw.GLFWWindowMaximizeCallback;

public class WindowMaximizeCallback extends GLFWWindowMaximizeCallback
{
	private WindowMaximizeEventHandler handler;

	public WindowMaximizeCallback(WindowMaximizeEventHandler handler)
	{
		this.handler = handler;
	}

	@Override
	public void invoke(long window, boolean maximized)
	{
		if(maximized)
		{
			handler.windowMaximizeEventHandler();
		}
		else
		{
			handler.windowUnmaximizeEventHandler();
		}
	}
}
