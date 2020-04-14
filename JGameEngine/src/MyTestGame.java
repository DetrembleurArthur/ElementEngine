import game.jgengine.exceptions.SysException;
import game.jgengine.graphics.Graphics;
import game.jgengine.graphics.shapes.Point;
import game.jgengine.sys.Game;
import game.jgengine.utils.Color;
import game.jgengine.utils.Colors;
import game.jgengine.utils.DPoint2D;

import static org.lwjgl.system.MemoryUtil.memUTF8;

public class MyTestGame extends Game
{
    @Override
    protected void load()
    {
        getPrimaryWindow().setClearColor(new Color(0, 200, 200));
        setFramerateLimit(60);
        getPrimaryWindow().setResizeable(false);
        getPrimaryWindow().setSize(500, 500);
        try
        {
           createSubWindow(false);
        } catch (SysException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void render(double dt)
    {
        getPrimaryWindow().clear();

        new Point(250, 250).draw();
        new Point(250, 250).draw();
        new Point(250, 250).draw();
        new Point(250, 250).draw();

        getPrimaryWindow().flip();
    }

    @Override
    protected void update(double dt)
    {
        System.out.println(1.f /dt);
    }

    @Override
    public void keyPressedEventHandler(int key)
    {
        getPrimaryWindow().setSize(800, 700);
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
    public void windowFocusEventHandler()
    {

    }

    @Override
    public void windowLooseFocusEventHandler()
    {

    }

    @Override
    public void windowCloseEventHandler()
    {
        System.out.println("Window closed");
    }

    @Override
    public void windowPosEventHandler(int xpos, int ypos)
    {

    }

    @Override
    public void windowIconifyEventHandler()
    {

    }

    @Override
    public void windowUniconifyEventHandler()
    {

    }

    @Override
    public void windowMaximizeEventHandler()
    {

    }

    @Override
    public void windowUnmaximizeEventHandler()
    {

    }

    @Override
    public void textInputEventHandler(int codepoint)
    {

    }

    @Override
    public void dropEventHandler(String[] items)
    {

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