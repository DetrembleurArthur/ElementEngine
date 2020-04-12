package game.jgengine.event;

import org.lwjgl.glfw.GLFWScrollCallback;

public class ScrollCallback extends GLFWScrollCallback
{
	private EventHandler handler;

	public ScrollCallback(EventHandler handler)
	{
		this.handler = handler;
	}


	@Override
	public void invoke(long window, double xoffset, double yoffset)
	{
		handler.scrollEventHandler(xoffset, yoffset);
	}
}
