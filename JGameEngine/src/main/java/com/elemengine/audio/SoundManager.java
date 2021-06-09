package com.elemengine.audio;

import com.elemengine.debug.Log;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALCCapabilities;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class SoundManager
{
	private static long device;
	private static long context;
	private static SoundListener listener;

	public static void initAL()
	{
		device = alcOpenDevice((ByteBuffer) null);
		if (device == NULL) {
			throw new IllegalStateException("Failed to open the default OpenAL device.");
		}
		ALCCapabilities deviceCaps = ALC.createCapabilities(device);
		context = alcCreateContext(device, (IntBuffer) null);
		if (context == NULL) {
			throw new IllegalStateException("Failed to create OpenAL context.");
		}
		alcMakeContextCurrent(context);
		AL.createCapabilities(deviceCaps);

		listener = new SoundListener();
		Log.print("Audio initialised");
	}

	public static void closeAL()
	{
		alcDestroyContext(context);
		alcCloseDevice(device);
		Log.print("Audio closed");
	}

	public static SoundListener getListener()
	{
		return listener;
	}
}
