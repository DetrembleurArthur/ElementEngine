package game.jgengine.sys;

import game.jgengine.audio.SoundBuffer;
import game.jgengine.audio.SoundManager;
import game.jgengine.event.handler.EventHandler;
import game.jgengine.exceptions.SysException;
import game.jgengine.graphics.shaders.Shader;
import game.jgengine.entity.GraphicElement;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.registry.Registry;
import game.jgengine.time.Time;
import game.mysql.Mysql;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_MULTISAMPLE;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public abstract class Game implements ResourcesManageable
{
	protected Window primaryWindow;
	private double framerateLimit = 30;
	private HashMap<String, Scene> scenes;
	private Scene currentScene;

	public static double DT = 0;
	public static Game GAME;

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

	public void startMysql(String ip, int port, String database, String timezone, String username, String password)
	{
		Mysql.connect(ip, port, database, timezone, username, password);
	}

	public void startMysql(String database, String password)
	{
		Mysql.connect(database, password);
	}

	private void stopMysql()
	{
		if(Mysql.connected())
		{
			Mysql.disconnect();
		}
	}


	public void switchTo3D()
	{
		glEnable(GL_DEPTH_TEST);
	}

	public void switchTo2d()
	{
		glDisable(GL_DEPTH_TEST);
	}

	private void initGraphics()
	{
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_MULTISAMPLE);
	}

	protected void enableAntialiasing(int sample)
	{
		glfwWindowHint(GLFW_SAMPLES, sample);
	}



	final protected void init() throws SysException
	{
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		enableAntialiasing(4);

		String osName = System.getProperty("os.name");
		System.out.println("OS: " + osName);
		if (osName.toUpperCase().contains("MAC"))
		{
			glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
			glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
			glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
			glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
		}

		primaryWindow = new Window(1400, 800, "Game");
		//primaryWindow.setEventHandler(this);
		primaryWindow.center();
		primaryWindow.active();
		GL.createCapabilities();
		initGraphics();
		initAudio();
		Registry.set("DEFAULT", Shader.DEFAULT);
		Registry.set("LIGHT", Shader.LIGHT);
		primaryWindow.simpleUpdateViewport();
		System.out.println("OpenGL version: " + glGetString(GL_VERSION));
		scenes = new HashMap<>();
		GAME = this;
	}

	private void initAudio()
	{
		SoundManager.initAL();
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

			currentScene.update(deltaTime);

			primaryWindow.clear();
			currentScene.render(deltaTime);
			primaryWindow.flip();

			if(framerateLimit != 0) while(Time.getElapsedTime() < beginTime + 1.0 / framerateLimit);
		}
	}

	@Override
	final public void closeResources()
	{
		for(var key : scenes.keySet())
		{
			scenes.get(key).close();
			scenes.get(key).closeResources();
		}
		primaryWindow.destroy();

		Registry.close();
		Rectangle.MODEL.destroy();
		SoundManager.closeAL();
		glfwTerminate();
		glfwSetErrorCallback(null).free();
		stopMysql();
	}

	final protected void run() throws SysException, InterruptedException
	{
		init();
		loadResources();

		if(currentScene != null)
			loop();
		else
			throw new SysException("No scene selected");

		closeResources();
	}

	public Window getPrimaryWindow()
	{
		return primaryWindow;
	}


	public void setFramerateLimit(double limit)
	{
		framerateLimit = limit;
	}

	/*@Override
	public final void windowResizedEventHandler(int width, int height)
	{
		getPrimaryWindow().aspectRatioUpdateViewport(width, height);
	}*/

	public void addScene(String id, Scene scene)
	{
		scenes.put(id, scene);
		currentScene = scene;
		scene.loadResources();
	}

	public Scene getScene(String id)
	{
		return scenes.get(id);
	}

	public Scene getCurrentScene()
	{
		return currentScene;
	}

	public void setCurrentScene(Scene scene)
	{
		if(currentScene != null)
			currentScene.close();
		currentScene = scene;
		if(scene instanceof Scene3D)
			switchTo3D();
		else
			switchTo2d();
		primaryWindow.setEventHandler(currentScene);
		currentScene.load();
	}

	public void setCurrentScene(String id)
	{
		setCurrentScene(scenes.get(id));
	}
}
