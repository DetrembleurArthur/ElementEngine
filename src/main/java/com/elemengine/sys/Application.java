package com.elemengine.sys;

import com.elemengine.audio.SoundManager;
import com.elemengine.exceptions.SysException;
import com.elemengine.graphics.shaders.Shader;
import com.elemengine.graphics.shapes.Rectangle;
import com.elemengine.registry.Registry;
import com.elemengine.time.Time;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_MULTISAMPLE;

public abstract class Application implements ResourcesManageable
{
    protected Window primaryWindow;
    private double framerateLimit = 30;
    private HashMap<String, Scene> scenes;
    private Scene currentScene;
    private final Signal signal = new Signal();

    public static double DT = 0;
    public static Application APPLICATION;

    static
    {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit())
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

    public static class Signal
    {
        public boolean next = false;
        public boolean previous = false;
        public String scene = null;

        void reset()
        {
            next = false;
            previous = false;
            scene = null;
        }

        boolean available()
        {
            return next || previous || scene != null;
        }
    }

    public static Application get()
    {
        return APPLICATION;
    }

    public Signal getSignal()
    {
        return signal;
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

    private void osAdaptation()
    {
        String osName = System.getProperty("os.name");
        System.out.println("OS: " + osName);
        if (osName.toUpperCase().contains("MAC"))
        {
            glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
            glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
            glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
            glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
        }
    }

    final protected void init() throws SysException
    {
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        //enableAntialiasing(4);
        osAdaptation();

        primaryWindow = new Window(1400, 800, "Game");
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
        APPLICATION = this;
    }

    private void initAudio()
    {
        SoundManager.initAL();
    }

    final protected void loop()
    {
        primaryWindow.show();

        double beginTime = Time.getElapsedTime();
        double deltaTime;

        while (primaryWindow.isOpen())
        {
            deltaTime = Time.getElapsedTime() - beginTime;
            beginTime = Time.getElapsedTime();
            APPLICATION.DT = deltaTime;

            primaryWindow.pollEvents();

            currentScene.update(deltaTime);

            primaryWindow.clear();
            currentScene.render(deltaTime);
            primaryWindow.flip();

            if (framerateLimit != 0) while (Time.getElapsedTime() < beginTime + 1.0 / framerateLimit) ;

            if(signal.available())
            {
                if(signal.next)
                    nextScene();
                else if(signal.previous)
                    previousScene();
                else if(signal.scene != null)
                    setCurrentScene(signal.scene);
                signal.reset();
            }

        }
    }

    @Override
    final public void closeResources()
    {
        for (var key : scenes.keySet())
        {
            scenes.get(key).close();
            scenes.get(key).closeResources();
        }
        primaryWindow.destroy();

        Registry.close();
        Rectangle.MODEL.destroy();
        SoundManager.closeAL();
        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
    }

    final protected void run() throws SysException
    {
        init();
        loadResources();

        if (currentScene != null)
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

    public void addScene(String id, Scene scene)
    {
        scenes.put(id, scene);
        scene.setSignal(signal);
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
        if (currentScene != null)
            currentScene.close();
        currentScene = scene;
        if (scene instanceof Scene3D)
            switchTo3D();
        else
            switchTo2d();
        primaryWindow.setEventHandler(currentScene.getEventCollector());
        currentScene.load();
    }

    public void setCurrentScene(String id)
    {
        setCurrentScene(scenes.get(id));
    }

    public void nextScene()
    {
        ArrayList<Scene> list = new ArrayList<>(scenes.values());
        int i = list.indexOf(currentScene);
        setCurrentScene(list.get((i + 1) % list.size()));
    }

    public void previousScene()
    {
        ArrayList<Scene> list = new ArrayList<>(scenes.values());
        int i = list.indexOf(currentScene);
        setCurrentScene(list.get(i == 0 ? list.size() - 1 : 0));
    }
}
