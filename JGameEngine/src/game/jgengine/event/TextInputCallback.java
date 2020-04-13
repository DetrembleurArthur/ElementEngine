package game.jgengine.event;

import org.lwjgl.glfw.GLFWCharCallback;

public class TextInputCallback extends GLFWCharCallback
{
	private EventHandler handler;

	public TextInputCallback(EventHandler handler)
	{
		this.handler = handler;
	}

	@Override
	public void invoke(long window, int codepoint)
	{
		handler.textInputEventHandler(codepoint);
	}
}
