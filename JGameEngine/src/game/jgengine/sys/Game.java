package game.jgengine.sys;

import game.jgengine.event.*;
import game.jgengine.exceptions.SysException;
import game.jgengine.graphics.Graphics;
import game.jgengine.graphics.Vertices;
import game.jgengine.graphics.shaders.Shader;
import game.jgengine.graphics.shapes.Triangle;
import game.jgengine.utils.Time;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public abstract class Game implements EventHandler
{
	private Window primaryWindow;

	private double framerateLimit = 30;
	private ArrayList<Shader> shaders = new ArrayList<>();

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


	private float[] vertices = {
		0.5f, -0.5f, 0.f,	1.f, 0.f, 0.f, 1.f,
		-0.5f, 0.5f, 0.f,	0.f, 1.f, 0.f, 1.f,
		0.5f, 0.5f, 0.f,	0.f, 0.f, 1.f, 1.f,
		-0.5f, -0.5f, 0.f,	1.f, 1.f, 0.f, 1.f
	};

	private int[] indexes = {
			2, 1, 0,
			0, 1, 3
	};

	private String vertexSrc = "#version 330 core\n" + "layout (location=0) in vec3 aPos;\n" + "layout (location=1) in vec4 aColor;\n" + "out vec4 fColor;\n" + "void main()\n" + "{\n" + "    fColor = aColor;\n" + "    gl_Position = vec4(aPos, 1.0);\n" + "}";
	private String fragSrc = "#version 330 core\n" + "in vec4 fColor;\n" + "out vec4 color;\n" + "void main()\n" + "{\n" + "    color = fColor;\n" + "}";

	Shader shader;
	Triangle triangle;
	int vao, vbo, ebo, vertexId, fragId, shaderProgram;
	final private void initGraphics()
	{
		shader = new Shader(
				"src/game/jgengine/graphics/shaders/vertex.glsl",
				"src/game/jgengine/graphics/shaders/fragment.glsl", true);
		shaders.add(shader);
		shader.loadUniform("number");
		shader.loadUniform("x");
		shader.loadUniform("y");


		triangle = new Triangle(0.5f, 0.5f, -0.5f, -0.5f, 0.5f, -0.5f);

		glEnable(GL_BLEND);
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
		//glOrtho(0, 500, 0, 500, -1, 1);

		initGraphics();
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

			getPrimaryWindow().clear();


			shader.start();
			shader.setUniformf1("number", (float)glfwGetTime());
			shader.setUniformf1("x", Mouse.getPosition(getPrimaryWindow()).x / getPrimaryWindow().getSize().x *2 -1);
			shader.setUniformf1("y", Mouse.getPosition(getPrimaryWindow()).y / getPrimaryWindow().getSize().y *2 -1);
			triangle.draw();

			shader.stop();

			getPrimaryWindow().flip();

			//render(deltaTime);

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
