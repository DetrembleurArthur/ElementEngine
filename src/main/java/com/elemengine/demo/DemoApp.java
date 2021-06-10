package com.elemengine.demo;

import com.elemengine.exceptions.SysException;
import com.elemengine.graphics.loaders.TextureLoader;
import com.elemengine.sys.Application;
import com.elemengine.sys.Window;
import org.joml.Vector4f;

public class DemoApp extends Application
{
    @Override
    public void loadResources()
    {
        Window.WINDOW.setClearColor(new Vector4f(0, 0.8f, 0.8f, 1));
        Window.WINDOW.setSize(Window.getScreenSize().mul(0.9f));
        Window.WINDOW.maintainSizeRatio(false);
        Window.WINDOW.setResizeable(true);
        Window.WINDOW.center();
        TextureLoader.loadDir("src/main/resources/sprites");
        setFramerateLimit(60);
        addScene("MAIN", new MainScene());
        addScene("CONTROLLER", new ControllerScene());
        setCurrentScene("CONTROLLER");
    }

    public static void main(String[] args) throws SysException, InterruptedException
    {
        new DemoApp().run();
    }
}
