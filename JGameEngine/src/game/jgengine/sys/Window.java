package game.jgengine.sys;

import game.jgengine.event.*;
import game.jgengine.exceptions.SysException;
import game.jgengine.graphics.Drawable;
import game.jgengine.utils.Color;
import game.jgengine.utils.IPoint2D;
import game.jgengine.utils.Size2D;
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window
{
	private long windowId = -1;
	private String title;
	private Cursor cursor = null;

	public static Window currentWindow = null;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		glfwSetWindowTitle(windowId, title);
		this.title = title;
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

	public static Size2D getScreenSize()
	{
		GLFWVidMode screen = glfwGetVideoMode(glfwGetPrimaryMonitor());
		return new Size2D(screen.width(), screen.height());
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

	public void setClearColor(Color color)
	{
		glfwMakeContextCurrent(windowId);
		glClearColor(color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio());
	}

	public void setContext()
	{
		glfwMakeContextCurrent(windowId);
		currentWindow = this;
	}

	public void flip()
	{
		glfwSwapBuffers(windowId);
	}

	public void clear()
	{
		setContext();
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

	public void setPosition(IPoint2D position)
	{
		glfwSetWindowPos(windowId, position.getX(), position.getY());
	}

	public void center()
	{
		Size2D screenSize = Window.getScreenSize();
		Size2D windowSize = getSize();
		setPosition((screenSize.getWidth() - windowSize.getWidth()) / 2,
				    (screenSize.getHeight() - windowSize.getHeight()) / 2);
	}

	public IPoint2D getPosition()
	{
		int[] x = new int[1], y = new int[1];
		glfwGetWindowPos(windowId, x, y);
		return new IPoint2D(x[0], y[0]);
	}

	public void setSize(int width, int height)
	{
		glfwSetWindowSize(windowId, width, height);
	}

	public void setSize(Size2D size)
	{
		glfwSetWindowSize(windowId, size.getWidth(), size.getHeight());
	}

	public void setSizeLimit(int minWidth, int minHeight, int maxWidth, int maxHeight)
	{
		glfwSetWindowSizeLimits(windowId, minWidth, minHeight, maxWidth, maxHeight);
	}

	public void setSizeLimit(Size2D mins, Size2D maxs)
	{
		glfwSetWindowSizeLimits(windowId, mins.getWidth(), mins.getHeight(), maxs.getWidth(), maxs.getHeight());
	}

	public void setAspectRatio(int number, int den)
	{
		glfwSetWindowAspectRatio(windowId, number, den);
	}

	public Size2D getSize()
	{
		int[] width = new int[1], height = new int[1];
		glfwGetWindowSize(windowId, width, height);
		return new Size2D(width[0], height[0]);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		Size2D size = getSize();
		try
		{
			Window window = new Window(size.getWidth(), size.getHeight(), title);
			window.setSize(getSize());
			window.setPosition((getPosition()));
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


	public void draw(Drawable drawable)
	{
		clear();
		drawable.draw();
		flip();
	}
}

