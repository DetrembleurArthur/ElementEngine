package game.jgengine.event;

import org.lwjgl.glfw.GLFWScrollCallback;

public class ScrollCallback extends GLFWScrollCallback
{
	private ScrollEventHandler handler;

	public ScrollCallback(ScrollEventHandler handler)
	{
		this.handler = handler;
	}


	@Override
	public void invoke(long window, double xoffset, double yoffset)
	{
		handler.scrollEventHandler(xoffset, yoffset);
	}
}
