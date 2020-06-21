package game.jgengine.graphics.rendering;

import game.jgengine.sys.Window;
import org.joml.Vector2i;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;

import java.nio.IntBuffer;

public class TargetTexture
{
	private int id;
	private int depthId;
	private Texture texture;
	private Vector2i size;

	public TargetTexture(Vector2i size)
	{
		this.size = size;
		id = GL30.glGenFramebuffers();
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, id);

		texture = new Texture(true, size);
		texture.bind();

		depthId = GL30.glGenRenderbuffers();
		GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, depthId);
		GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, GL11.GL_DEPTH_COMPONENT, size.x, size.y);
		GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT, GL30.GL_RENDERBUFFER, depthId);

		IntBuffer buffer = BufferUtils.createIntBuffer(1);
		buffer.put(GL30.GL_COLOR_ATTACHMENT0);
		buffer.flip();
		GL32.glFramebufferTexture(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, texture.getId(), 0);
		GL20.glDrawBuffers(buffer);
		buffer.clear();

		texture.unbind();
		unbind();
	}

	public void bind()
	{
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, id);
		GL11.glViewport(0, 0, size.x, size.y);
	}

	public void unbind()
	{
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
		GL11.glViewport(0, 0, (int)Window.WINDOW.getSize().x, (int)Window.WINDOW.getSize().y);
	}


	public int getId()
	{
		return id;
	}

	public int getDepthId()
	{
		return depthId;
	}

	public Texture getTexture()
	{
		return texture;
	}

	public Vector2i getSize()
	{
		return size;
	}
}
