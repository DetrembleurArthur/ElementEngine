import game.jgengine.exceptions.SysException;
import game.jgengine.sys.Game;
import game.jgengine.utils.Color;

import static org.lwjgl.system.MemoryUtil.memUTF8;

public class MyTestGame extends Game
{
    double spd = 0.0;
    double g = 0.0981;

    @Override
    protected void load()
    {
        getPrimaryWindow().setClearColor(new Color(0, 200, 200));
        setFramerateLimit(60);
        getPrimaryWindow().setResizeable(false);
        getPrimaryWindow().setSize(1400, 800);
        getPrimaryWindow().center();
    }

    @Override
    protected void render(double dt)
    {
        getPrimaryWindow().clear();


        getPrimaryWindow().flip();
    }

    @Override
    protected void update(double dt)
    {
        getPrimaryWindow().setTitle("fps " + Double.toString(1.f /dt));
        getPrimaryWindow().move(0, (int)spd);
        spd += g;
        if(getPrimaryWindow().getPosition().y -800>= 1000)
        {
            spd = 0;
            g = -g;
        }
    }

    @Override
    public void keyPressedEventHandler(int key)
    {

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