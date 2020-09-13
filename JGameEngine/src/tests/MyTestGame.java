package tests;

import game.jgengine.exceptions.SysException;
import game.jgengine.graphics.loaders.TextureLoader;
import game.jgengine.graphics.texts.Font;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Game;
import game.jgengine.sys.Window;
import org.joml.Vector4f;


public class MyTestGame extends Game
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

        Registry.set("impact", new Font("assets/fonts/impact.fnt"));

        TextureLoader.load("bricks", "assets/bricks.png");
        TextureLoader.load("background", "assets/background.jpg");

        addScene("MAIN", new MainScene());
        setCurrentScene("MAIN");
    }



    public static void main(String[] args)
    {
        try
        {
            new MyTestGame().run();
        }
        catch (SysException | InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}