package game.jgengine.graphics.rendering;


import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3i;
import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load;

public class Texture
{
	private String path;
	private int id;
	private Vector2f dimension;

	public Texture(String path, boolean rgba)
	{
		this.path = path;

		id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);

		init(path, rgba);

		glBindTexture(GL_TEXTURE_2D, 0);
	}

	public Texture(int id, boolean rgba, Vector2i size)
	{
		this.id = id;
		glBindTexture(GL_TEXTURE_2D, id);

		init(size, rgba, 0);

		glBindTexture(GL_TEXTURE_2D, 0);
	}

	public Texture(boolean rgba, Vector2i size)
	{
		this.id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);

		init(size, rgba, 0);

		glBindTexture(GL_TEXTURE_2D, 0);
	}

	public Texture(Image image, boolean rgba)
	{
		this.id = glGenTextures();
		bind();
		init(new Vector2i(image.getSize()), rgba, image.toByteBuffer());
		unbind();
	}



	public void init(String path, boolean rgba)
	{
		IntBuffer width = BufferUtils.createIntBuffer(1);
		IntBuffer height = BufferUtils.createIntBuffer(1);
		IntBuffer channels = BufferUtils.createIntBuffer(1);
		ByteBuffer image = stbi_load(path, width, height, channels, 0);
		if(image != null)
		{
			init(new Vector2i(width.get(0), height.get(0)), rgba, image);
			stbi_image_free(image);
		}
	}

	public void init(Vector2i size, boolean rgba, ByteBuffer image)
	{
		//glTexImage2D(GL_TEXTURE_2D, 0,GL_DEPTH_COMPONENT24, 1024, 768, 0,GL_DEPTH_COMPONENT, GL_FLOAT, 0); => profondeur
		glTexImage2D(GL_TEXTURE_2D, 0, rgba ? GL_RGBA : GL_RGB, size.x, size.y, 0, rgba ? GL_RGBA : GL_RGB, GL_UNSIGNED_BYTE, image);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		dimension = new Vector2f(size.x, size.y);
	}

	public void init(Vector2i size, boolean rgba, int rid)
	{
		glTexImage2D(GL_TEXTURE_2D, 0, rgba ? GL_RGBA : GL_RGB, size.x, size.y, 0, rgba ? GL_RGBA : GL_RGB, GL_UNSIGNED_BYTE, rid);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		dimension = new Vector2f(size.x, size.y);
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

	public Vector2f getDimension()
	{
		return dimension;
	}

	public float getWidth()
	{
		return dimension.x;
	}

	public float getHeight()
	{
		return dimension.y;
	}

	public Vector2f[] getUV2D(float x, float y, float w, float h)
	{
		return new Vector2f[]
		{
			new Vector2f(x / getWidth(), y / getHeight()), //TOP LEFT
			new Vector2f(x / getWidth(), y / getHeight() + h / getHeight()), //BOTTOM LEFT,
			new Vector2f(x / getWidth() + w / getWidth(), y / getHeight() + h / getHeight()), //BOTTOM RIGHT
			new Vector2f(x / getWidth() + w / getWidth(), y / getHeight()), //TOP RIGHT
		};
	}

	public int getId()
	{
		return id;
	}
}

