package game.jgengine.graphics.gui.widgets;

import game.jgengine.debug.Logs;
import game.jgengine.event.Input;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.gui.event.MouseButtonReleasedEvent;
import game.jgengine.graphics.texts.Font;
import game.jgengine.sys.Window;
import game.jgengine.time.StaticTimer;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

public class Entry extends Label
{
	private boolean focused;
	private float speedKey;
	private StaticTimer timer;

	public Entry(Font font, float width)
	{
		super(font, "your text here");
		getShape().setTextWidth(width);
		focused = false;
		onMouseButtonReleased(sender -> focused = true);
		speedKey = 0;
		timer = new StaticTimer(speedKey);
		timer.activate();
	}

	@Override
	public void run()
	{
		super.run();
		if(focused)
		{
			if(Input.isButtonPressed() && !getShape().contains(Mouse.getPosition(getEvent(MouseButtonReleasedEvent.class).getCamera())))
			{
				focused = false;
			}
		}
	}

	public void updateKeys(int keyCode)
	{
		if (focused)
		{
			if(Input.isKeyPressed(Window.WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT))
				keyCode += 32;
			char keyId = (char)keyCode;
			if (timer.isFinished())
			{
				Logs.print(keyCode);
				if(keyCode == GLFW.GLFW_KEY_BACKSPACE)
				{
					String text = new String(getShape().getText());
					int len = text.length();
					if(!text.isEmpty())
						textProperty.set(text.substring(0, len-1));
				}
				else if(keyCode == GLFW.GLFW_KEY_ENTER)
				{
					textProperty.set(getShape().getText() + "\n");
				}
				else
				{
					if(getShape().getFont().getGlyphs().containsKey(keyId))
					{
						textProperty.set(getShape().getText() + new String(new char[]{keyId}));
					}
				}
				timer.activate();
			}
		}
	}

	public boolean isFocused()
	{
		return focused;
	}

	public void setFocused(boolean focused)
	{
		this.focused = focused;
	}

	public float getSpeedKey()
	{
		return speedKey;
	}

	public void setSpeedKey(float speedKey)
	{
		this.speedKey = speedKey;
	}
}
