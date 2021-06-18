package com.elemengine.graphics.texts;

import com.elemengine.components.event.EventManagerComponent;
import com.elemengine.components.properties.TextPropertyComponent;
import com.elemengine.debug.Log;
import com.elemengine.event.Input;
import com.elemengine.event.Mouse;
import com.elemengine.components.event.MouseButtonReleasedEvent;
import com.elemengine.graphics.camera.Camera2D;
import com.elemengine.time.StaticTimer;
import org.lwjgl.glfw.GLFW;

public class Entry extends Text
{
	private boolean focused;
	private float speedKey;
	private StaticTimer timer;

	public Entry(Font font, float height, Camera2D camera2D)
	{
		super(font, "your text here");
		setTextHeight(height);
		focused = false;
		addComponent(new EventManagerComponent(this));
		getComponent(EventManagerComponent.class).onMouseButtonReleased(sender -> focused = true, camera2D);
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
			if(Input.isButtonPressed() && !contains(Mouse.getPosition(getComponent(EventManagerComponent.class).getEvent(MouseButtonReleasedEvent.class).getCamera())))
			{
				focused = false;
			}
		}
	}

	public void setEntryText(String text)
	{
		TextPropertyComponent component = getComponent(TextPropertyComponent.class);
		if(component != null)
			component.textProperty.set(text);
		else
			setText(text);
	}

	public void updateTextKeys(int keyCode)
	{
		if (focused)
		{
			char keyId = (char)keyCode;
			if (timer.isFinished())
			{
				if(getFont().getGlyphs().containsKey(keyId))
				{
					setEntryText(getText() + new String(new char[]{keyId}));
				}
				timer.activate();
			}
		}
	}

	public void updatePhysicalKeys(int keyCode)
	{
		if (focused)
		{
			if (timer.isFinished())
			{
				Log.print(keyCode);
				if(keyCode == GLFW.GLFW_KEY_BACKSPACE)
				{
					String text = new String(getText());
					int len = text.length();
					if(text.length() > 0)
						setEntryText(text.substring(0, len-1));
				}
				else if(keyCode == GLFW.GLFW_KEY_ENTER)
				{
					setEntryText(getText() + "\n");
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
