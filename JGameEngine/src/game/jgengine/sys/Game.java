package game.jgengine.sys;

import game.jgengine.event.*;
import game.jgengine.exceptions.SysException;
import game.jgengine.utils.Color;
import game.jgengine.utils.Size2D;
import game.jgengine.utils.Time;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;

import static java.lang.Thread.sleep;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public abstract class Game implements EventHandler
{
	private Window primaryWindow;
	private ArrayList<Window> windows = new ArrayList<>();
	private double framerateLimit = 30;

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


	final protected void init() throws SysException
	{
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

		primaryWindow = new Window(500, 500, "Game");
		primaryWindow.setEventHandler(this);
		primaryWindow.center();
		primaryWindow.active();

		GL.createCapabilities();
		//glOrtho(0, 500, 0, 500, -1, 1);


		System.out.println("OpenGL version: " + glGetString(GL_VERSION));
	}

	public Window createSubWindow(boolean closeable) throws SysException
	{
		Window window = new Window(500, 500, "Game");
		windows.add(window);
		if(closeable)
		{
			window.setWindowCloseCallback(new WindowCloseCallback(() -> closeSubWindow(window)));
		}
		window.center();
		window.active();
		window.show();
		return window;
	}

	public void addSubWindow(Window window, boolean closeable)
	{
		windows.add(window);
		if(closeable)
		{
			window.setWindowCloseCallback(new WindowCloseCallback(() -> closeSubWindow(window)));
		}
		window.active();
		window.show();
	}

	final protected void loop() throws InterruptedException
	{
		primaryWindow.show();

		double beginTime = Time.getElapsedTime();
		double endTime = beginTime;
		double deltaTime = 0.f;

		while(primaryWindow.isOpen())
		{
			deltaTime = Time.getElapsedTime() - beginTime;
			beginTime = Time.getElapsedTime();

			primaryWindow.pollEvents();

			update(deltaTime);
			render(deltaTime);

			if(framerateLimit != 0) while(Time.getElapsedTime() < beginTime + 1.0 / framerateLimit);
		}
	}

	final protected void close()
	{
		primaryWindow.destroy();
		for(Window window : windows) window.destroy();
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}

	final protected void run() throws SysException, InterruptedException
	{
		init();
		load();

		loop();

		close();
	}

	public Window getPrimaryWindow()
	{
		return primaryWindow;
	}


	public void setFramerateLimit(double limit)
	{
		framerateLimit = limit;
	}


	public void closeSubWindow(Window window)
	{
		windows.remove(window);
		window.close();
		window.destroy();
	}

	public Window getSubWindow(int i)
	{
		return windows.get(i);
	}
}
