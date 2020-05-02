package game.jgengine.graphics.shaders;

import org.joml.Vector2f;
import org.lwjgl.BufferUtils;

import java.io.Console;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load;

public class Texture
{
	private String path;
	private int id;

	public Texture(String path, boolean rgba)
	{
		this.path = path;

		id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

		IntBuffer width = BufferUtils.createIntBuffer(1);
		IntBuffer height = BufferUtils.createIntBuffer(1);
		IntBuffer channels = BufferUtils.createIntBuffer(1);
		ByteBuffer image = stbi_load(path, width, height, channels, 0);
		if(image != null)
		{
			glTexImage2D(GL_TEXTURE_2D, 0, rgba ? GL_RGBA : GL_RGB, width.get(0), height.get(0), 0, rgba ? GL_RGBA : GL_RGB, GL_UNSIGNED_BYTE, image);

			stbi_image_free(image);
		}
		else
		{
			System.out.println("Image error");
		}


	}

	public void active()
	{
		glActiveTexture(GL_TEXTURE0);
	}

	public void bind()
	{
		glBindTexture(GL_TEXTURE_2D, id);
	}

	public void unbind()
	{
		glBindTexture(GL_TEXTURE_2D, 0);
	}

	public void destroy()
	{
		glDeleteTextures(id);
	}
}
