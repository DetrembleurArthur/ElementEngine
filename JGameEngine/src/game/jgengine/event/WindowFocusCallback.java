package game.jgengine.event;

import org.lwjgl.glfw.GLFWWindowFocusCallback;

public class WindowFocusCallback extends GLFWWindowFocusCallback
{
	private EventHandler handler;

	public WindowFocusCallback(EventHandler handler)
	{
		this.handler = handler;
	}

	@Override
	public void invoke(long window, boolean focused)
	{
		if(focused)
		{
			handler.windowFocusedEventHandler();
		}
		else
		{
			handler.windowLoosedFocusEventHandler();
		}
	}
}
