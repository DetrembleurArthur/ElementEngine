import game.jgengine.event.Input;
import game.jgengine.event.Mouse;
import game.jgengine.exceptions.SysException;
import game.jgengine.graphics.GameObject;
import game.jgengine.graphics.Line;
import game.jgengine.graphics.Mesh;
import game.jgengine.graphics.Renderer;
import game.jgengine.graphics.shaders.Shader;
import game.jgengine.graphics.shaders.Texture;
import game.jgengine.graphics.shapes.Cube;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.sys.Game;
import game.jgengine.utils.Colors;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_LINE_LOOP;


public class MyTestGame extends Game
{
    GameObject gelem;
    Line gelem2;


    boolean centered = false;

    @Override
    protected void load()
    {
        getPrimaryWindow().setClearColor(Colors.BLACK);
        setFramerateLimit(60);
        getPrimaryWindow().setResizeable(true);
        getPrimaryWindow().center();


        Texture texture = new Texture("assets/bricks.png", true);
        addTexture(texture);
        gelem = new Cube(texture);
        gelem.setPosition(new Vector3f(0, 0, 5));
        gelem2 = new Line();
        gelem.setFillColor(Colors.WHITE);

        gelem2.setBeginPosition(new Vector3f());
        gelem2.setEndPosition(new Vector3f(0f, 20f, 100f));
        gelem2.setWidth(5);
        gelem2.getMesh().setColor(0, Colors.TURQUOISE);


        addShape(gelem);
        addShape(gelem2);

    }

    @Override
    protected void render(double dt)
    {
        var pos = Mouse.getPosition(getPrimaryWindow());

        getPrimaryWindow().clear();

        getTextureUColorRenderer().render(gelem, camera);
        getShapeRenderer().render(gelem2, camera);

        getPrimaryWindow().flip();
    }

    @Override
    protected void update(double dt)
    {
        gelem.getRotation().y += 0.5;
        gelem.getRotation().x -= 0.5;
        if(Input.isLeftButtonPressed(getPrimaryWindow()))
        {
        }
        getPrimaryWindow().setTitle("fps " + Double.toString(1.f /dt));

        if(Input.isKeyPressed(getPrimaryWindow(), GLFW_KEY_LEFT))
        {
            if(Input.isRightButtonPressed(getPrimaryWindow()))
                camera.leftaxis();
            else
                camera.left();
        }

        if(Input.isKeyPressed(getPrimaryWindow(), GLFW_KEY_RIGHT))
        {
            if(Input.isRightButtonPressed(getPrimaryWindow()))
                camera.rightaxis();
            else
                camera.right();
        }

        if(Input.isKeyPressed(getPrimaryWindow(), GLFW_KEY_UP))
        {
            if(Input.isRightButtonPressed(getPrimaryWindow()))
                camera.towardaxis();
            else
                camera.toward();
        }

        if(Input.isKeyPressed(getPrimaryWindow(), GLFW_KEY_DOWN))
        {
            if(Input.isRightButtonPressed(getPrimaryWindow()))
                camera.backwardaxis();
            else
                camera.backward();
        }

        if(Input.isKeyPressed(getPrimaryWindow(), GLFW_KEY_RIGHT_SHIFT))
        {
            camera.downaxis();
        }

        if(Input.isKeyPressed(getPrimaryWindow(), GLFW_KEY_SPACE))
        {
            camera.upaxis();
        }


    }

    @Override
    public void keyPressedEventHandler(int key)
    {
        if(Input.isKeyPressed(getPrimaryWindow(), GLFW_KEY_ENTER))
        {
            centered = !centered;
            if(centered)
            {
                camera.setOldMouse(Mouse.getPosition(getPrimaryWindow()));
                getPrimaryWindow().disableCursor();
            }
            else
                getPrimaryWindow().resetCursor();

        }
        else
        {

        }
    }

    @Override
    public void cursorMovedEventHandler(double xpos, double ypos)
    {
        var pos = Mouse.getPosition(getPrimaryWindow());

        if(centered)
        {
            camera.update(new Vector2f((float)xpos, (float)ypos));
            //Mouse.setPosition(getPrimaryWindow(), new Vector2f(getPrimaryWindow().getSize().x / 2, getPrimaryWindow().getSize().y / 2));
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