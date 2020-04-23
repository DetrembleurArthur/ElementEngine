import game.jgengine.event.Input;
import game.jgengine.event.Mouse;
import game.jgengine.exceptions.SysException;
import game.jgengine.graphics.shapes.*;
import game.jgengine.utils.Cursor;
import game.jgengine.sys.Game;
import game.jgengine.utils.Color;
import game.jgengine.utils.Colors;
import org.joml.Random;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;


public class MyTestGame extends Game
{

    Triangle triangle;
    Rectangle rectangle;

    @Override
    protected void load()
    {
        getPrimaryWindow().setClearColor(new Color(0, 200, 200));
        setFramerateLimit(60);
        getPrimaryWindow().setResizeable(true);
        //getPrimaryWindow().setSize(1400, 800);
        getPrimaryWindow().center();

        getPrimaryWindow().setCursor(new Cursor("src/game/jgengine/sys/default-cursor.png"));

        rectangle = new Rectangle(new Vector2f(450, 150), new Vector2f(500, 500), Colors.LIME);
        addShape(rectangle);
    }

    @Override
    protected void render(double dt)
    {
        var pos = Mouse.getPosition(getPrimaryWindow());

        getPrimaryWindow().clear();

        rectangle.draw(camera);

        getPrimaryWindow().flip();
    }

    @Override
    protected void update(double dt)
    {
        getPrimaryWindow().setTitle("fps " + Double.toString(1.f /dt));
        var pos = Mouse.getPosition(getPrimaryWindow());
        if(Input.isKeyPressed(getPrimaryWindow(), GLFW_KEY_LEFT))
        {
            camera.getPosition().x -= 150 * dt;
        }

        if(Input.isKeyPressed(getPrimaryWindow(), GLFW_KEY_RIGHT))
        {
            camera.getPosition().x += 150 * dt;
        }

        if(Input.isKeyPressed(getPrimaryWindow(), GLFW_KEY_UP))
        {
            camera.getPosition().y -= 150 * dt;
        }

        if(Input.isKeyPressed(getPrimaryWindow(), GLFW_KEY_DOWN))
        {
            camera.getPosition().y += 150 * dt;
        }
    }

    @Override
    public void keyPressedEventHandler(int key)
    {

    }



    @Override
    public void buttonPressedEventHandler(int button)
    {
        //rectangle.setColor(Colors.random());
        rectangle.setGradient(Colors.LIME, Colors.MAGENTA, Colors.BLUE, Colors.YELLOW);
    }

    @Override
    public void windowResizedEventHandler(int width, int height)
    {

        getCamera().adjustProjection(new Vector2f(width, height));
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