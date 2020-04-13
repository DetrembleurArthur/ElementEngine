import game.jgengine.event.Input;
import game.jgengine.exceptions.SysException;
import game.jgengine.sys.Cursor;
import game.jgengine.sys.Game;
import game.jgengine.utils.Color;

public class MyTestGame extends Game
{
    @Override
    protected void load()
    {
        getWindow().setSize(800, 800);
        //setResizeable(false);
        setClearColor(new Color(0, 200, 200));
        setFramerateLimit(60);

        getWindow().setCursor(new Cursor("C:\\Users\\mb624\\Desktop\\progSpecimen_.png"));
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
        String keyName = Input.getKeyName(key);
        if(keyName.equals("a"))
        {
            getWindow().disableCursor();
        }
        else if(keyName.equals("z"))
        {
            getWindow().hideCursor();
        }
        else
        {
            getWindow().resetCursor();
        }
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

    }

    @Override
    public void windowLoosedFocusEventHandler()
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