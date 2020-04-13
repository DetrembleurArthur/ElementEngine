package game.jgengine.event;

import org.lwjgl.glfw.GLFWWindowMaximizeCallback;

public class WindowMaximizeCallback extends GLFWWindowMaximizeCallback
{
	private EventHandler handler;

	public WindowMaximizeCallback(EventHandler handler)
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
