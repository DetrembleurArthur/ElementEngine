package game.jgengine.event.handler;

import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;

public class KeyCallback extends GLFWKeyCallback
{
	private KeyEventHandler handler;

	public KeyCallback(KeyEventHandler handler)
	{
		this.handler = handler;
	}

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods)
	{
		switch(action)
		{
			case GLFW_PRESS:
				handler.keyPressedEventHandler(key);
				break;
			case GLFW_RELEASE:
				handler.keyReleasedEventHandler(key);
				break;
			case GLFW_REPEAT:
				handler.keyRepeatedEventHandler(key);
				break;
		}
	}
}
