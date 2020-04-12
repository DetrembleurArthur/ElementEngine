package game.jgengine.sys;

import game.jgengine.event.*;
import game.jgengine.exceptions.SysException;
import game.jgengine.utils.Color;
import game.jgengine.utils.Size2D;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static java.lang.Thread.sleep;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public abstract class Game implements EventHandler
{
	private Window window;

	static
	{
		GLFWErrorCallback.createPrint(System.err).set();
		if(!glfwInit())
		{
			try
			{
				throw new SysException("Can not initialized GLFW");
			} catch (SysException e)
			{
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	abstract protected void load();
	abstract protected void render(double dt);
	abstract protected void update(double dt);

	public void setResizeable(boolean b)
	{
		glfwWindowHint(GLFW_RESIZABLE, b ? GLFW_TRUE : GLFW_FALSE);
		try
		{
			window = (Window) window.clone();
			window.setKeyCallback(new KeyCallback(this));
			window.setButtonCallback(new ButtonCallback(this));
			window.setCursorPosCallback(new CursorPosCallback(this));
			window.setScrollCallback(new ScrollCallback(this));
			window.setCursorEnteredCallback(new CursorEnterCallback(this));
			window.setWindowResizedCallback(new WindowResizeCallback(this));
			window.setWindowFocusCallback(new WindowFocusCallback(this));
			glfwDestroyWindow(glfwGetCurrentContext());
			window.active();
			window.show();
		} catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
	}

	final public void setClearColor(Color color)
	{
		glClearColor(color.getRedRatio(), color.getGreenRatio(), color.getBlueRatio(), color.getAlphaRatio());
	}

	final protected void init() throws SysException
	{
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

		window = new Window(500, 500, "Game");
		window.setKeyCallback(new KeyCallback(this));
		window.setButtonCallback(new ButtonCallback(this));
		window.setCursorPosCallback(new CursorPosCallback(this));
		window.setScrollCallback(new ScrollCallback(this));
		window.setCursorEnteredCallback(new CursorEnterCallback(this));
		window.setWindowResizedCallback(new WindowResizeCallback(this));
		window.setWindowFocusCallback(new WindowFocusCallback(this));
		Size2D winSize = window.getSize();
		Size2D screenSize = Window.getScreenSize();
		glfwSetWindowPos(window.getId(), (screenSize.getWidth() - winSize.getWidth()) / 2,
				(screenSize.getHeight() - winSize.getHeight()) / 2);
		window.active();

		GL.createCapabilities();
	}

	final protected void loopEvents()
	{
		window.pollEvents();
	}

	final protected void loop() throws InterruptedException
	{
		window.show();
		double lastTime = glfwGetTime();
		double deltaTime = 0;
		while(window.isOpen())
		{
			deltaTime = glfwGetTime() - lastTime;
			lastTime = glfwGetTime();

			loopEvents();
			update(deltaTime);
			render(deltaTime);

			while(glfwGetTime() < lastTime + 1.0 / 60.0);

		}
	}

	final protected void close()
	{
		glfwDestroyWindow(window.getId());
		glfwTerminate();
	}

	final protected void run() throws SysException, InterruptedException
	{
		init();
		load();

		loop();

		close();
	}

	public Window getWindow()
	{
		return window;
	}

	public void clear()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glfwSwapBuffers(window.getId());
	}
}
