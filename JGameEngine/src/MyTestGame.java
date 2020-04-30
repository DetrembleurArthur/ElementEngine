import game.jgengine.event.Input;
import game.jgengine.event.Mouse;
import game.jgengine.exceptions.SysException;
import game.jgengine.graphics.Camera;
import game.jgengine.graphics.Mesh;
import game.jgengine.graphics.Renderer;
import game.jgengine.graphics.shaders.Shader;
import game.jgengine.graphics.shaders.Textured;
import game.jgengine.graphics.shapes.*;
import game.jgengine.graphics.vertex.GraphicElement;
import game.jgengine.utils.Cursor;
import game.jgengine.sys.Game;
import game.jgengine.utils.Color;
import game.jgengine.utils.Colors;
import org.joml.Random;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glViewport;


public class MyTestGame extends Game
{
    Textured gelem;
    boolean centered = false;
    @Override
    protected void load()
    {
        getPrimaryWindow().setClearColor(new Color(0, 200, 200));
        setFramerateLimit(60);
        getPrimaryWindow().setResizeable(true);
        //getPrimaryWindow().setSize(1400, 800);
        //glViewport(0, 0, (int)getPrimaryWindow().getSize().x, (int)getPrimaryWindow().getSize().y);
        getPrimaryWindow().center();

        getPrimaryWindow().setCursor(new Cursor("src/game/jgengine/sys/default-cursor.png"));

        glViewport(0, 0, 1400, 800);
        gelem = new Textured(new Vector3f(), new Vector3f(), new Vector3f(1, 1, 1), new Mesh(
                new float[]{0.5f, -0.5f, 0f,    1f, 1f, 1f, 1f,    1,1,
                            -0.5f, 0.5f, 0f,    1f, 1f, 1f, 1f,       0,0,
                            0.5f, 0.5f, 0f,     1f, 1f, 1f, 1f,        1,0,
                            -0.5f, -0.5f, 0f,   1f, 1f, 1f, 1f,         0,1},
                new int[]{2, 1, 0,0,1,3}
        ), "assets/apple.png");

        camera = new Camera(new Vector3f(0, 0, 1), primaryWindow.getSize());
        System.out.println(primaryWindow.getSize());

        addShape(gelem);
    }

    @Override
    protected void render(double dt)
    {
        var pos = Mouse.getPosition(getPrimaryWindow());

        getPrimaryWindow().clear();

        new Renderer(Shader.DEFAULT, getPrimaryWindow()).render(gelem, camera);
        getPrimaryWindow().flip();
    }

    @Override
    protected void update(double dt)
    {
        getPrimaryWindow().setTitle("fps " + Double.toString(1.f /dt));

        if(Input.isKeyPressed(getPrimaryWindow(), GLFW_KEY_LEFT))
        {
            camera.left();
        }

        if(Input.isKeyPressed(getPrimaryWindow(), GLFW_KEY_RIGHT))
        {
            camera.right();
        }

        if(Input.isKeyPressed(getPrimaryWindow(), GLFW_KEY_UP))
        {
            camera.up();
        }

        if(Input.isKeyPressed(getPrimaryWindow(), GLFW_KEY_DOWN))
        {
            camera.down();
        }

        if(Input.isKeyPressed(getPrimaryWindow(), GLFW_KEY_RIGHT_SHIFT))
        {
            camera.out();
        }

        if(Input.isKeyPressed(getPrimaryWindow(), GLFW_KEY_SPACE))
        {
            camera.in();
        }


    }

    @Override
    public void keyPressedEventHandler(int key)
    {
        if(Input.isKeyPressed(getPrimaryWindow(), GLFW_KEY_ENTER))
            centered = !centered;
    }

    @Override
    public void cursorMovedEventHandler(double xpos, double ypos)
    {
        var pos = Mouse.getPosition(getPrimaryWindow());

        if(centered)
        {
            camera.update(new Vector2f((float)xpos, (float)ypos));
            Mouse.setPosition(getPrimaryWindow(), new Vector2f(getPrimaryWindow().getSize().x / 2, getPrimaryWindow().getSize().y / 2));
        }
    }

    @Override
    public void buttonPressedEventHandler(int button)
    {
        //rectangle.setColor(Colors.random());
    }

    @Override
    public void windowResizedEventHandler(int width, int height)
    {

       glViewport(0, 0, (int)getPrimaryWindow().getSize().x, (int)getPrimaryWindow().getSize().y);
       camera.center = new Vector2f(getPrimaryWindow().getSize().x/2,getPrimaryWindow().getSize().y/2);
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