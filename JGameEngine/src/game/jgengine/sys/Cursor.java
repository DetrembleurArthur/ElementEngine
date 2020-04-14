package game.jgengine.sys;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.glfw.GLFW.*;

public class Cursor
{
	public static final int ARROW = GLFW_ARROW_CURSOR;
	public static final int IBEAM = GLFW_IBEAM_CURSOR;
	public static final int CROSSHAIR = GLFW_CROSSHAIR_CURSOR;
	public static final int HAND = GLFW_HAND_CURSOR;
	public static final int HRESIZE = GLFW_HRESIZE_CURSOR;
	public static final int VRESIZE = GLFW_VRESIZE_CURSOR;

	private long cursorId;

	public Cursor(int cursorType)
	{
		cursorId = glfwCreateStandardCursor(cursorType);
	}

	public Cursor(int width, int height, byte[] buffer)
	{

		ByteBuffer bbuffer = BufferUtils.createByteBuffer((width*4) * (height * 4));
		bbuffer.put(buffer);
		bbuffer.flip();
		GLFWImage image = GLFWImage.malloc();
		image.set(width,height,bbuffer);


		cursorId = glfwCreateCursor(image, 0, 0);
	}

	public Cursor(String filename)
	{
		BufferedImage bufferedImage = null;
		try
		{
			bufferedImage = ImageIO.read(new File(filename));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		ByteBuffer pixels = BufferUtils.createByteBuffer(bufferedImage.getWidth() * bufferedImage.getHeight() * 4);
		for(int i = 0; i < bufferedImage.getHeight(); i++)
		{
			for(int j = 0; j< bufferedImage.getWidth(); j++)
			{
				Color color = new Color(bufferedImage.getRGB(j, i), true);
				pixels.put((byte)color.getRed());
				pixels.put((byte)color.getGreen());
				pixels.put((byte)color.getBlue());
				pixels.put((byte)color.getAlpha());
			}
		}
		pixels.flip();
		GLFWImage image = GLFWImage.malloc();
		image.set(bufferedImage.getWidth(), bufferedImage.getHeight(), pixels);
		cursorId = glfwCreateCursor(image, 0, 0);
	}

	public void destroy()
	{
		glfwDestroyCursor(cursorId);
		cursorId = 0;
	}

	public long getId()
	{
		return cursorId;
	}
}
