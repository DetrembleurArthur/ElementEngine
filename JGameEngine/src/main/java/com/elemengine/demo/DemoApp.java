package com.elemengine.demo;

import com.elemengine.exceptions.SysException;
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
        setFramerateLimit(60);
        addScene("MAIN", new MainScene());
        setCurrentScene("MAIN");
    }

    public static void main(String[] args) throws SysException, InterruptedException
    {
        new DemoApp().run();
    }
}
