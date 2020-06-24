package game.jgengine.sys;

import game.jgengine.event.handler.EventHandler;
import game.jgengine.exceptions.SysException;
import game.jgengine.graphics.shaders.Shader;
import game.jgengine.entity.GraphicElement;
import game.jgengine.registry.Registry;
import game.jgengine.time.Time;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;

import static java.lang.Thread.sleep;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public abstract class Game implements EventHandler
{
	protected Window primaryWindow;

	private double framerateLimit = 30;
	private ArrayList<GraphicElement> shapes = new ArrayList<>();
	public static double DT = 0;

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


	public void addShape(GraphicElement shape)
	{
		shapes.add(shape);
	}
	final private void initGraphics()
	{
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);


		//glEnable(GL_DEPTH_TEST);
	}



	final protected void init() throws SysException
	{
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

		String osName = System.getProperty("os.name");
		System.out.println("OS: " + osName);
		if (osName.contains("Mac"))
		{
			glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
			glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
			glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
			glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
		}

		primaryWindow = new Window(1400, 800, "Game");
		primaryWindow.setEventHandler(this);
		primaryWindow.center();
		primaryWindow.active();
		GL.createCapabilities();
		initGraphics();
		Registry.set("DEFAULT", Shader.DEFAULT);
		primaryWindow.simpleUpdateViewport();

		System.out.println("OpenGL version: " + glGetString(GL_VERSION));
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
			Game.DT = deltaTime;

			primaryWindow.pollEvents();

			update(deltaTime);

			render(deltaTime);

			if(framerateLimit != 0) while(Time.getElapsedTime() < beginTime + 1.0 / framerateLimit);
		}
	}

	final protected void close()
	{

		primaryWindow.destroy();

		for(GraphicElement shape : shapes)
		{
			shape.destroy();
		}

		Registry.close();
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
}
