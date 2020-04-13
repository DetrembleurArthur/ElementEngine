package game.jgengine.event;

import game.jgengine.sys.Window;
import org.lwjgl.glfw.GLFWWindowCloseCallback;

public class WindowCloseCallback extends GLFWWindowCloseCallback
{
	private EventHandler handler;

	public WindowCloseCallback(EventHandler handler)
	{
		this.handler = handler;
	}

	@Override
	public void invoke(long window)
	{
		handler.windowCloseEventHandler();
	}
}
