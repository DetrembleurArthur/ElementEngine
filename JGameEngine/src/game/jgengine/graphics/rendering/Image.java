package game.jgengine.graphics.rendering;

import game.jgengine.sys.Window;
import org.joml.Vector2i;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load;

public class Image
{
	private BufferedImage imageBuffer;
	private Vector2i size;

	public Image()
	{

	}

	public Image(String imagePath)
	{
		loadFromPath(imagePath);
	}

	private void loadFromPath(String imagePath)
	{
		File file = new File(imagePath);
		if(file.exists())
		{
			IntBuffer width = BufferUtils.createIntBuffer(1);
			IntBuffer height = BufferUtils.createIntBuffer(1);
			IntBuffer channels = BufferUtils.createIntBuffer(1);
			ByteBuffer image = stbi_load(imagePath, width, height, channels, 0);
			if(image != null)
			{
				size = new Vector2i(width.get(0), height.get(0));
				init(image);
				stbi_image_free(image);
			}
		}
	}

	private void init(ByteBuffer buffer)
	{
		imageBuffer = new BufferedImage(size.x, size.y, BufferedImage.TYPE_INT_ARGB);
		for(int i = 0; i < size.x; i++)
		{
			for(int j = 0; j < size.y; j++)
			{
				int pos = (j * size.x + i) * 4;
				int r = buffer.get(pos) & 0xff;
				int g = buffer.get(pos + 1) & 0xff;
				int b = buffer.get(pos + 2) & 0xff;
				int a = buffer.get(pos + 3) & 0xff;
				imageBuffer.setRGB(i, size.y - 1 - j, (a << 24) | (r << 16) | (g << 8) | b);
			}
		}
	}

	public ByteBuffer toByteBuffer()
	{
		int[] pixels = new int[size.x * size.y];
		imageBuffer.getRGB(0, 0, size.x, size.y, pixels, 0, size.x);
		ByteBuffer buffer = BufferUtils.createByteBuffer(size.x * size.y * 4);
		for(int i = 0; i < size.y; i++)
		{
			for(int j = 0; j < size.x; j++)
			{
				int px = pixels[(size.y - 1 - i) * size.x + j];
				buffer.put((byte)((px >> 16) & 0xff));
				buffer.put((byte)((px >> 8) & 0xff));
				buffer.put((byte)(px & 0xff));
				buffer.put((byte)((px >> 24) & 0xff));
			}
		}
		buffer.flip();
		return buffer;
	}

	public Texture toTexture(boolean rgba)
	{
		return new Texture(this, rgba);
	}

	public Vector2i getSize()
	{
		return size;
	}

	public void write(String filename)
	{
		try
		{
			ImageIO.write(imageBuffer, "PNG", new File(filename));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
