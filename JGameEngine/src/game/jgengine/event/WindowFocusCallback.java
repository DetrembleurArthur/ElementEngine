package game.jgengine.event;

import org.lwjgl.glfw.GLFWWindowFocusCallback;

public class WindowFocusCallback extends GLFWWindowFocusCallback
{
	private WindowFocusEventHandler handler;

	public WindowFocusCallback(WindowFocusEventHandler handler)
	{
		this.handler = handler;
	}

	@Override
	public void invoke(long window, boolean focused)
	{
		if(focused)
		{
			handler.windowFocusEventHandler();
		}
		else
		{
			handler.windowLooseFocusEventHandler();
		}
	}
}
