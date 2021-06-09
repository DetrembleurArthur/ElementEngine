package com.elemengine.audio;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;
import org.lwjgl.stb.STBVorbis;
import org.lwjgl.stb.STBVorbisInfo;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.stb.STBVorbis.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class SoundBuffer
{
	private int id;
	private ShortBuffer buffer;
	private ByteBuffer vorbis;

	public SoundBuffer(String audioFileName)
	{
		id = AL10.alGenBuffers();
		try(STBVorbisInfo info = STBVorbisInfo.malloc())
		{
			ShortBuffer buffer = readVorbis(audioFileName, 32 * 1024, info);
			alBufferData(id, info.channels() == 1 ? AL_FORMAT_MONO16 : AL_FORMAT_STEREO16, buffer, info.sample_rate());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public int getId()
	{
		return id;
	}

	public ShortBuffer getBuffer()
	{
		return buffer;
	}

	public ByteBuffer getVorbis()
	{
		return vorbis;
	}

	public void destroy() {
		alDeleteBuffers(id);
		if (buffer != null) {
			MemoryUtil.memFree(buffer);
		}
	}

	private ShortBuffer readVorbis(String resource, int bufferSize, STBVorbisInfo info) throws Exception {
		try (MemoryStack stack = MemoryStack.stackPush()) {
			vorbis = ioResourceToByteBuffer(resource, bufferSize);
			IntBuffer error = stack.mallocInt(1);
			long decoder = stb_vorbis_open_memory(vorbis, error, null);
			if (decoder == NULL) {
				throw new RuntimeException("Failed to open Ogg Vorbis file. Error: " + error.get(0));
			}

			stb_vorbis_get_info(decoder, info);

			int channels = info.channels();

			int lengthSamples = stb_vorbis_stream_length_in_samples(decoder);

			buffer = MemoryUtil.memAllocShort(lengthSamples);

			buffer.limit(stb_vorbis_get_samples_short_interleaved(decoder, channels, buffer) * channels);
			stb_vorbis_close(decoder);

			return buffer;
		}
	}

	public static ByteBuffer ioResourceToByteBuffer(String resource, int bufferSize) throws IOException {
		ByteBuffer buffer;

		Path path = Paths.get(resource);
		if (Files.isReadable(path)) {
			try (SeekableByteChannel fc = Files.newByteChannel(path)) {
				buffer = MemoryUtil.memAlloc((int) fc.size() + 1);
				while (true)
				{
					try
					{
						if (fc.read(buffer) == -1) break;
					} catch (IOException e)
					{
						e.printStackTrace();
					}
					;
				}
			}
		} else {
			try (
					InputStream source = new FileInputStream(new File(resource));
					ReadableByteChannel rbc = Channels.newChannel(source)) {
				buffer = MemoryUtil.memAlloc(bufferSize);

				while (true) {
					int bytes = rbc.read(buffer);
					if (bytes == -1) {
						break;
					}
					if (buffer.remaining() == 0) {
						buffer = resizeBuffer(buffer, buffer.capacity() * 2);
					}
				}
			}
		}

		buffer.flip();
		return buffer;
	}

	private static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
		ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
		buffer.flip();
		newBuffer.put(buffer);
		return newBuffer;
	}
}
