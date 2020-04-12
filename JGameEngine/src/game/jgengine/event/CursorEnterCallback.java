package game.jgengine.event;

import org.lwjgl.glfw.GLFWCursorEnterCallback;

public class CursorEnterCallback extends GLFWCursorEnterCallback
{
	private EventHandler handler;

	public CursorEnterCallback(EventHandler handler)
	{
		this.handler = handler;
	}

	@Override
	public void invoke(long window, boolean entered)
	{
		if(entered)
		{
			handler.cursorEnteredEventHandler();
		}
		else
		{
			handler.cursorExitedEventHandler();
		}
	}
}
