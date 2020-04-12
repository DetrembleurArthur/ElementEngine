import game.jgengine.event.Cursor;
import game.jgengine.exceptions.SysException;
import game.jgengine.sys.Game;
import game.jgengine.sys.Window;
import game.jgengine.utils.Color;
import game.jgengine.utils.Colors;

import static org.lwjgl.glfw.GLFW.*;

public class MyTestGame extends Game
{
    int x = 0, y = 0;
    @Override
    protected void load()
    {
        getWindow().setSize(1000, 800);
        //setResizeable(false);
        setClearColor(new Color(0, 200, 200));
    }

    @Override
    protected void render(double dt)
    {
        clear();
    }

    @Override
    protected void update(double dt)
    {

    }

    @Override
    public void keyPressedEventHandler(int key)
    {
        if(key == GLFW_KEY_SPACE)
            setClearColor(Colors.CYAN.getNegative());
        else if(key == GLFW_KEY_UP)
            setClearColor(Colors.CYAN);
    }

    @Override
    public void keyReleasedEventHandler(int key)
    {

    }

    @Override
    public void keyRepeatedEventHandler(int key)
    {

    }

    @Override
    public void buttonPressedEventHandler(int button)
    {

    }

    @Override
    public void buttonReleasedEventHandler(int button)
    {

    }

    @Override
    public void buttonRepeatedEventHandler(int button)
    {

    }

    @Override
    public void cursorMovedEventHandler(double x, double y)
    {

    }

    @Override
    public void scrollEventHandler(double xoffset, double yoffset)
    {

    }

    @Override
    public void cursorEnteredEventHandler()
    {

    }

    @Override
    public void cursorExitedEventHandler()
    {

    }

    @Override
    public void windowResizedEventHandler(int width, int height)
    {

    }

    @Override
    public void windowFocusedEventHandler()
    {
        System.out.println("Focus");
        setClearColor(Colors.LIME);
    }

    @Override
    public void windowLoosedFocusEventHandler()
    {
        System.out.println("Lost focus");
        setClearColor(Colors.RED);
    }

    public static void main(String[] args)
    {   try
        {
            new MyTestGame().run();
        }
        catch (SysException | InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}