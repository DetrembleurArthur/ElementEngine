package game.jgengine.event.handler;

import org.lwjgl.glfw.GLFWMouseButtonCallback;

import static org.lwjgl.glfw.GLFW.*;

public class ButtonCallback extends GLFWMouseButtonCallback
{
	private ButtonEventHandler handler;

	public ButtonCallback(ButtonEventHandler handler)
	{
		this.handler = handler;
	}

	@Override
	public void invoke(long window, int button, int action, int mods)
	{
		switch(action)
		{
			case GLFW_PRESS:
				handler.buttonPressedEventHandler(button);
				break;
			case GLFW_RELEASE:
				handler.buttonReleasedEventHandler(button);
				break;
			case GLFW_REPEAT:
				handler.buttonRepeatedEventHandler(button);
				break;
		}
	}
}
