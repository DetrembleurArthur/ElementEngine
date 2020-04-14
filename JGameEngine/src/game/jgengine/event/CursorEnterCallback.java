package game.jgengine.event;

import org.lwjgl.glfw.GLFWCursorEnterCallback;

public class CursorEnterCallback extends GLFWCursorEnterCallback
{
	private CursorEnterEventHandler handler;

	public CursorEnterCallback(CursorEnterEventHandler handler)
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
