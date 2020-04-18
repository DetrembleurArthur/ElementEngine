import game.jgengine.event.Input;
import game.jgengine.event.Mouse;
import game.jgengine.exceptions.SysException;
import game.jgengine.graphics.shaders.Shader;
import game.jgengine.graphics.shapes.*;
import game.jgengine.net.Frame;
import game.jgengine.utils.Cursor;
import game.jgengine.sys.Game;
import game.jgengine.utils.Color;
import game.jgengine.utils.Colors;
import game.jgengine.utils.Vec2f;


public class MyTestGame extends Game
{

    Shader shader;
    Line line;
    Pixel pixel;

    @Override
    protected void load()
    {
        getPrimaryWindow().setClearColor(new Color(0, 200, 200));
        setFramerateLimit(60);
        getPrimaryWindow().setResizeable(true);
        getPrimaryWindow().setSize(1400, 800);
        getPrimaryWindow().center();

        getPrimaryWindow().setCursor(new Cursor("src/game/jgengine/sys/default-cursor.png"));

        line = new Line(new Vec2f(0f,0f), new Vec2f(0.5f, 0.5f), Colors.ORANGE);
        addShape(line);
    }

    @Override
    protected void render(double dt)
    {
        var pos = getPrimaryWindow().normal(Mouse.getPosition(getPrimaryWindow()));

        getPrimaryWindow().clear();


        //line.draw(Shader.DEFAULT);

        LineLoop.draw(new Vec2f[]
                {
                        new Vec2f(-0.5f, -0.5f), new Vec2f(0f, 0.5f), new Vec2f(0.2f, -0.2f)
                }, Colors.RED, Colors.GREEN, 3);


        getPrimaryWindow().flip();
    }

    @Override
    protected void update(double dt)
    {
        getPrimaryWindow().setTitle("fps " + Double.toString(1.f /dt));
       // var pos = getPrimaryWindow().normal(Mouse.getPosition(getPrimaryWindow()));


    }

    @Override
    public void keyPressedEventHandler(int key)
    {

    }

    @Override
    public void buttonPressedEventHandler(int button)
    {
        var pos = getPrimaryWindow().normal(Mouse.getPosition(getPrimaryWindow()));
        if(Input.isLeftButtonPressed(getPrimaryWindow()))
        {
            getPrimaryWindow().setClearColor(Colors.random());
        }
    }

    @Override
    public void windowResizedEventHandler(int width, int height)
    {
        getPrimaryWindow().updateViewport();
    }

    @Override
    public void windowCloseEventHandler()
    {
        System.out.println("Window closed");
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