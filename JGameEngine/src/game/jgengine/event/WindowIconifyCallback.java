package game.jgengine.event;

import org.lwjgl.glfw.GLFWWindowIconifyCallback;

public class WindowIconifyCallback extends GLFWWindowIconifyCallback
{
	private EventHandler handler;

	public WindowIconifyCallback(EventHandler handler)
	{
		this.handler = handler;
	}

	@Override
	public void invoke(long window, boolean iconified)
	{
		if(iconified)
		{
			handler.windowIconifyEventHandler();
		}
		else
		{
			handler.windowUniconifyEventHandler();
		}
	}
}
