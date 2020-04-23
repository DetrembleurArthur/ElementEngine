package game.jgengine.sys;

import game.jgengine.event.handler.EventHandler;
import game.jgengine.exceptions.SysException;
import game.jgengine.graphics.Camera2D;
import game.jgengine.graphics.shaders.Shader;
import game.jgengine.graphics.shapes.Shape;
import game.jgengine.utils.Time;
import org.joml.Vector2f;
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
	protected Camera2D camera;

	private double framerateLimit = 30;
	private ArrayList<Shader> shaders = new ArrayList<>();
	private ArrayList<Shape> shapes = new ArrayList<>();

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


	public void addShader(Shader shader)
	{
		shaders.add(shader);
	}

	public void addShape(Shape shape)
	{
		shapes.add(shape);
	}

	final private void initGraphics()
	{
		addShader(Shader.DEFAULT);
		glEnable(GL_BLEND);
		glEnable(GL_DEPTH_TEST);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}



	final protected void init() throws SysException
	{
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

		primaryWindow = new Window(1400, 800, "Game");
		primaryWindow.setEventHandler(this);
		primaryWindow.center();
		primaryWindow.active();
		GL.createCapabilities();
		initGraphics();

		camera = new Camera2D(new Vector2f(), primaryWindow.getSize());

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

			primaryWindow.pollEvents();

			update(deltaTime);



			render(deltaTime);

			if(framerateLimit != 0) while(Time.getElapsedTime() < beginTime + 1.0 / framerateLimit);
		}
	}

	final protected void close()
	{
		primaryWindow.destroy();
		for(Shader shader : shaders)
		{
			shader.destroy();
		}
		for(Shape shape : shapes)
		{
			shape.destroy();
		}
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

	public Camera2D getCamera()
	{
		return camera;
	}
}
