package game.jgengine.event;

import game.jgengine.sys.Window;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;

public class WindowResizeCallback extends GLFWFramebufferSizeCallback
{
	private EventHandler handler;

	public WindowResizeCallback(EventHandler handler)
	{
		this.handler = handler;
	}

	@Override
	public void invoke(long window, int width, int height)
	{
		handler.windowResizedEventHandler(width, height);
	}
}
