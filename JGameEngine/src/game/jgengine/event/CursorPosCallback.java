package game.jgengine.event;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class CursorPosCallback extends GLFWCursorPosCallback
{
	private EventHandler handler;

	public CursorPosCallback(EventHandler handler)
	{
		this.handler = handler;
	}

	@Override
	public void invoke(long window, double xpos, double ypos)
	{
		handler.cursorMovedEventHandler(xpos, ypos);
	}
}
