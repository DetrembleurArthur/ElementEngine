package game.jgengine.event.handler;

import org.lwjgl.glfw.GLFWWindowIconifyCallback;

public class WindowIconifyCallback extends GLFWWindowIconifyCallback
{
	private WindowIconifyEventHandler handler;

	public WindowIconifyCallback(WindowIconifyEventHandler handler)
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
