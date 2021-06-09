package com.elemengine.event.handler;

import org.lwjgl.glfw.GLFWDropCallback;
import org.lwjgl.system.Pointer;

import static org.lwjgl.system.MemoryUtil.memGetAddress;
import static org.lwjgl.system.MemoryUtil.memUTF8;

public class DropCallback extends GLFWDropCallback
{
	private DropEventHandler handler;

	public DropCallback(DropEventHandler handler)
	{
		this.handler = handler;
	}

	@Override
	public void invoke(long window, int count, long names)
	{
		String[] items = new String[count];
		for(int i = 0; i < count; i++)
		{
			items[i] = memUTF8(memGetAddress(names + Pointer.POINTER_SIZE * i));
		}
		handler.dropEventHandler(items);
	}
}
