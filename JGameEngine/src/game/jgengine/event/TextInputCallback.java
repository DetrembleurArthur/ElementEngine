package game.jgengine.event;

import org.lwjgl.glfw.GLFWCharCallback;

public class TextInputCallback extends GLFWCharCallback
{
	private TextInputEventHandler handler;

	public TextInputCallback(TextInputEventHandler handler)
	{
		this.handler = handler;
	}

	@Override
	public void invoke(long window, int codepoint)
	{
		handler.textInputEventHandler(codepoint);
	}
}
