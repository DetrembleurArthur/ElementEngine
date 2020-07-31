package game.jgengine.sys;

import game.jgengine.debug.Logs;
import game.jgengine.event.handler.*;
import game.jgengine.exceptions.SysException;
import game.jgengine.utils.Cursor;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.system.MemoryUtil.memFree;

public class Window
{
	private long windowId = -1;
	private String title;
	private Cursor cursor = null;
	private Vector2f monitorSize;
	private float targetApectRatio;
	private Vector2f aspectRationSize;
	public static Window WINDOW = null;
	private boolean sizeRatioEnable;
	private Vector2f aspectRationPosition;
	private Vector4f clearColor;

	public boolean isSizeRatioEnable()
	{
		return sizeRatioEnable;
	}

	public Vector2f getAspectRationPosition()
	{
		return aspectRationPosition;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		glfwSetWindowTitle(windowId, title);
		this.title = title;
	}

	public float getAspectRatio()
	{
		return getSize().x / getSize().y;
	}

	public Window() throws SysException
	{
		this(300, 300, "Game title");
	}

	public Window(int width, int height, String title) throws SysException
	{
		windowId = glfwCreateWindow(width, height, title, NULL, NULL);
		if(windowId == NULL)
		{
			throw new SysException("Can not create a Window");
		}
		this.title = title;
		GLFWVidMode mode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		monitorSize = new Vector2f(mode.width(), mode.height());
		targetApectRatio = (float)mode.width() / (float)mode.height();
		aspectRationPosition = new Vector2f();
		aspectRationSize = new Vector2f(windowId, height);
	}

	public long getWindowId()
	{
		return windowId;
	}

	public Cursor getCursor()
	{
		return cursor;
	}

	public Vector2f getMonitorSize()
	{
		return monitorSize;
	}

	public float getTargetApectRatio()
	{
		return targetApectRatio;
	}

	public void destroy()
	{
		if(cursor != null) cursor.destroy();
		glfwFreeCallbacks(windowId);
		glfwDestroyWindow(windowId);
		windowId = 0;
	}

	public void setResizeable(boolean state)
	{
		glfwSetWindowAttrib(windowId, GLFW_RESIZABLE, state ? GLFW_TRUE : GLFW_FALSE);
	}

	public boolean isResizeable()
	{
		return glfwGetWindowAttrib(windowId, GLFW_RESIZABLE) == 1;
	}

	public void setDecorated(boolean state)
	{
		glfwSetWindowAttrib(windowId, GLFW_DECORATED, state ? GLFW_TRUE : GLFW_FALSE);
	}

	public void setCursor(Cursor cursor)
	{
		glfwSetCursor(windowId, cursor.getId());
		if(this.cursor != null) this.cursor.destroy();
		this.cursor = cursor;
	}

	public boolean isHovering()
	{
		return glfwGetWindowAttrib(windowId, GLFW_HOVERED) == 1;
	}

	public static Vector2f getScreenSize()
	{
		GLFWVidMode screen = glfwGetVideoMode(glfwGetPrimaryMonitor());
		return new Vector2f(screen.width(), screen.height());
	}

	public long getId()
	{
		return windowId;
	}

	void active()
	{
		setContext();
		glfwSwapInterval(1);
	}

	public void setClearColor(Vector4f color)
	{
		glfwMakeContextCurrent(windowId);
		glClearColor(color.x, color.y, color.z, color.w);
		clearColor = color;
	}

	public Vector4f getClearColor()
	{
		return clearColor;
	}

	public void setContext()
	{
		glfwMakeContextCurrent(windowId);
		WINDOW = this;
	}

	public void flip()
	{
		glfwSwapBuffers(windowId);
	}

	public void clear()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	public void show()
	{
		glfwShowWindow(windowId);
	}

	public void hide()
	{
		glfwHideWindow(windowId);
	}

	public void setOpacity(float opacity)
	{
		glfwSetWindowOpacity(windowId, opacity);
	}

	public float getOpacity()
	{
		return glfwGetWindowOpacity(windowId);
	}

	public void focus()
	{
		glfwFocusWindow(windowId);
	}

	public void requestAttention()
	{
		glfwRequestWindowAttention(windowId);
	}

	public boolean isOpen()
	{
		return !glfwWindowShouldClose(windowId);
	}

	public void close()
	{
		glfwSetWindowShouldClose(windowId, true);
	}

	public void blockClosing()
	{
		glfwSetWindowShouldClose(windowId, false);
	}

	public void disableCursor()
	{
		glfwSetInputMode(windowId, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
	}

	public void resetCursor()
	{
		glfwSetInputMode(windowId, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
	}

	public void hideCursor()
	{
		glfwSetInputMode(windowId, GLFW_CURSOR, GLFW_CURSOR_HIDDEN);
	}

	public void setRawMouseMotion(boolean state)
	{
		glfwSetInputMode(windowId, GLFW_RAW_MOUSE_MOTION, state ? GLFW_TRUE : GLFW_FALSE);
	}

	public void setPosition(int x, int y)
	{
		glfwSetWindowPos(windowId, x, y);
	}

	public void setPosition(Vector2i position)
	{
		glfwSetWindowPos(windowId, position.x, position.y);
	}

	public void move(int x, int y)
	{
		var p = getPosition();
		setPosition((int)p.x + x, (int)p.y + y);
	}

	public void center()
	{
		Vector2f screenSize = Window.getScreenSize();
		Vector2f windowSize = getSize();
		setPosition((int)(screenSize.x - windowSize.x) / 2,
				(int)(screenSize.y - windowSize.y) / 2);
	}

	public Vector2f getCenter()
	{
		var size = getSize();
		return new Vector2f(size.x / 2f, size.y / 2f);
	}

	public Vector2i getPosition()
	{
		int[] x = new int[1], y = new int[1];
		glfwGetWindowPos(windowId, x, y);
		return new Vector2i(x[0], y[0]);
	}

	public void setSize(int width, int height)
	{
		glfwSetWindowSize(windowId, width, height);
		simpleUpdateViewport();
	}

	public void setSize(Vector2f size)
	{
		glfwSetWindowSize(windowId, (int)size.x, (int)size.y);
	}

	public void setSizeLimit(int minWidth, int minHeight, int maxWidth, int maxHeight)
	{
		glfwSetWindowSizeLimits(windowId, minWidth, minHeight, maxWidth, maxHeight);
	}

	public void setSizeLimit(Vector2i mins, Vector2i maxs)
	{
		glfwSetWindowSizeLimits(windowId, mins.x, mins.y, maxs.x, maxs.y);
	}

	public void setAspectRatio(int number, int den)
	{
		glfwSetWindowAspectRatio(windowId, number, den);
	}

	public Vector2f getSize()
	{
		int[] width = new int[1], height = new int[1];
		glfwGetWindowSize(windowId, width, height);
		return new Vector2f(width[0], height[0]);
	}

	public Vector2i getiSize()
	{
		int[] width = new int[1], height = new int[1];
		glfwGetWindowSize(windowId, width, height);
		return new Vector2i(width[0], height[0]);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		Vector2f size = getSize();
		try
		{
			Window window = new Window((int)size.x, (int)size.y, title);
			window.setSize(getSize());
			window.setPosition(getPosition());
			return window;
		} catch (SysException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public void iconify()
	{
		glfwIconifyWindow(windowId);
	}

	public void restore()
	{
		glfwRestoreWindow(windowId);
	}

	public void maximize()
	{
		glfwMaximizeWindow(windowId);
	}

	void pollEvents()
	{
		glfwPollEvents();
	}

	public void setKeyCallback(KeyCallback keyCallback)
	{
		glfwSetKeyCallback(windowId, keyCallback);
	}

	public void setButtonCallback(ButtonCallback buttonCallback)
	{
		glfwSetMouseButtonCallback(windowId, buttonCallback);
	}

	public void setCursorPosCallback(CursorPosCallback cursorPosCallback)
	{
		glfwSetCursorPosCallback(windowId, cursorPosCallback);
	}

	public void setScrollCallback(ScrollCallback scrollCallback)
	{
		glfwSetScrollCallback(windowId, scrollCallback);
	}

	public void setCursorEnteredCallback(CursorEnterCallback cursorEnteredCallback)
	{
		glfwSetCursorEnterCallback(windowId, cursorEnteredCallback);
	}

	public void setWindowResizedCallback(WindowResizeCallback windowResizedCallback)
	{
		glfwSetFramebufferSizeCallback(windowId, windowResizedCallback);
	}

	public void setWindowFocusCallback(WindowFocusCallback windowFocusCallback)
	{
		glfwSetWindowFocusCallback(windowId, windowFocusCallback);
	}

	public void setWindowCloseCallback(WindowCloseCallback windowCloseCallback)
	{
		glfwSetWindowCloseCallback(windowId, windowCloseCallback);
	}

	public void setWindowPosCallback(WindowPosCallback windowPosCallback)
	{
		glfwSetWindowPosCallback(windowId, windowPosCallback);
	}

	public void setWindowIconifyCallback(WindowIconifyCallback windowIconifyCallback)
	{
		glfwSetWindowIconifyCallback(windowId, windowIconifyCallback);
	}

	public void setWindowMaximizeCallback(WindowMaximizeCallback windowMaximizeCallback)
	{
		glfwSetWindowMaximizeCallback(windowId, windowMaximizeCallback);
	}

	public void setTextInputCallback(TextInputCallback textInputCallback)
	{
		glfwSetCharCallback(windowId, textInputCallback);
	}

	public void setDropCallback(DropCallback dropCallback)
	{
		glfwSetDropCallback(windowId, dropCallback);
	}

	public void setEventHandler(EventHandler handler)
	{
		setKeyCallback(new KeyCallback(handler));
		setButtonCallback(new ButtonCallback(handler));
		setCursorPosCallback(new CursorPosCallback(handler));
		setScrollCallback(new ScrollCallback(handler));
		setCursorEnteredCallback(new CursorEnterCallback(handler));
		setWindowResizedCallback(new WindowResizeCallback(handler));
		setWindowFocusCallback(new WindowFocusCallback(handler));
		setWindowCloseCallback(new WindowCloseCallback(handler));
		setWindowPosCallback(new WindowPosCallback(handler));
		setWindowIconifyCallback(new WindowIconifyCallback(handler));
		setWindowMaximizeCallback(new WindowMaximizeCallback(handler));
		setTextInputCallback(new TextInputCallback(handler));
		setDropCallback(new DropCallback(handler));
	}

	public void simpleUpdateViewport()
	{
		var size = getSize();
		glViewport(0, 0, (int)size.x, (int)size.y);
	}

	public void aspectRatioUpdateViewport(float w, float h)
	{
		int aspectWidth = (int)w;
		int aspectHeight = (int)((float)aspectWidth / getTargetApectRatio());
		if(aspectHeight > h)
		{
			aspectHeight = (int) h;
			aspectWidth = (int)((float)aspectHeight * getTargetApectRatio());
		}

		int vpx = (int)(((float) w / 2f) - ((float)aspectWidth / 2f));
		int vpy = (int)(((float) h / 2f) - ((float)aspectHeight / 2f));



		aspectRationSize = new Vector2f(aspectWidth, aspectHeight);
		glViewport(vpx, vpy, aspectWidth, aspectHeight);
		if(sizeRatioEnable)
		{
			glfwSetWindowSize(windowId, (int) aspectWidth, (int) aspectHeight);
			aspectRationPosition = new Vector2f(0, 0);
		}
		else
		{
			glfwSetWindowSize(windowId, (int) w, (int) h);
			aspectRationPosition = new Vector2f(vpx, vpy);
		}
	}

	public Vector2f getAspectRatioSize()
	{
		return aspectRationSize;
	}

	public void maintainSizeRatio(boolean state)
	{
		sizeRatioEnable = state;
	}

	public void takeScreenShot(String dst)
	{
		var size = getiSize();
		ByteBuffer buffer = MemoryUtil.memAlloc(size.x * size.y * 4);
		GL11.glReadPixels(0, 0, size.x, size.y, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
		BufferedImage image = new BufferedImage(size.x, size.y, BufferedImage.TYPE_INT_ARGB);
		for(int i = 0; i < size.x; i++)
		{
			for(int j = 0; j < size.y; j++)
			{
				int pos = (j * size.x + i) * 4;
				int r = buffer.get(pos) & 0xff;
				int g = buffer.get(pos + 1) & 0xff;
				int b = buffer.get(pos + 2) & 0xff;
				int a = buffer.get(pos + 3) & 0xff;
				image.setRGB(i, size.y - 1 - j, (a << 24) | (r << 16) | (g << 8) | b);
			}
		}
		try
		{
			ImageIO.write(image, "PNG", new File(dst));
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		memFree(buffer);
	}
}

