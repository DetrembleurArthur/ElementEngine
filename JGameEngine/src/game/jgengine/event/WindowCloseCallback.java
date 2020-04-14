package game.jgengine.event;

import game.jgengine.sys.Window;
import org.lwjgl.glfw.GLFWWindowCloseCallback;

public class WindowCloseCallback extends GLFWWindowCloseCallback
{
	private WindowCloseEventHandler handler;

	public WindowCloseCallback(WindowCloseEventHandler handler)
	{
		this.handler = handler;
	}

	@Override
	public void invoke(long window)
	{
		handler.windowCloseEventHandler();
	}
}
