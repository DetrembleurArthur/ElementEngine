import game.jgengine.event.Input;
import game.jgengine.event.Mouse;
import game.jgengine.exceptions.SysException;
import game.jgengine.graphics.Camera;
import game.jgengine.graphics.Mesh;
import game.jgengine.graphics.Renderer;
import game.jgengine.graphics.shaders.Shader;
import game.jgengine.graphics.shaders.Texture;
import game.jgengine.graphics.shaders.Textured;
import game.jgengine.graphics.vertex.GraphicElement;
import game.jgengine.utils.Cursor;
import game.jgengine.sys.Game;
import game.jgengine.utils.Color;
import game.jgengine.utils.Colors;
import game.jgengine.utils.Time;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glViewport;

/*
*  t = new Textured(new Vector3f(), new Vector3f(), new Vector3f(1,1,1), new Mesh(
                new float[]{
                        // VO
                        -0.5f,  0.5f,  0.5f,    1f, 1f, 1f, 1f,     0f,0f,
                        // V1
                        -0.5f, -0.5f,  0.5f,    1f, 1f, 1f, 1f,     0f,1f,
                        // V2
                        0.5f, -0.5f,  0.5f,    1f, 1f, 1f, 1f,      1f,1f,
                        // V3
                        0.5f,  0.5f,  0.5f,    1f, 1f, 1f, 1f,      1f,0f,
                        // V4
                        -0.5f,  0.5f, -0.5f,    1f, 1f, 1f, 1f,     0f,0f,
                        // V5
                        0.5f,  0.5f, -0.5f,    1f, 1f, 1f, 1f,      0f,1f,
                                // V6
                        -0.5f, -0.5f, -0.5f,    1f, 1f, 1f, 1f,     1f,1f,
                        // V7
                        0.5f, -0.5f, -0.5f,    1f, 1f, 1f, 1f,      1f,0f
                },
                new int[]{
                        // Front face
                        0, 1, 3, 3, 1, 2,
                        // Top Face
                        4, 0, 3, 5, 4, 3,
                        // Right face
                        3, 2, 7, 5, 3, 7,
                        // Left face
                        6, 1, 0, 6, 0, 4,
                        // Bottom face
                        2, 1, 6, 2, 6, 7,
                        // Back face
                        7, 6, 4, 7, 4, 5,
                },
                Mesh.DIMENSION_3, Mesh.RGBA, 2
        ), "assets/beautiful.png", false);
* */
public class MyTestGame extends Game
{
    Textured gelem;
    ArrayList<GraphicElement> list = new ArrayList<>();
    boolean centered = false;
    GraphicElement t;
    @Override
    protected void load()
    {
        getPrimaryWindow().setClearColor(new Color(0, 0, 50));
        setFramerateLimit(60);
        getPrimaryWindow().setResizeable(true);
        getPrimaryWindow().center();

        //getPrimaryWindow().setCursor(new Cursor("src/game/jgengine/sys/default-cursor.png"));

        //glViewport(0, 0, 1400, 800);
       /* gelem = new Textured(new Vector3f(), new Vector3f(), new Vector3f(1f, 1f, 1f), new Mesh(
                new float[]{0.5f, -0.5f, 0f,    1f, 1f, 1f, 1f,    1,1,
                            -0.5f, 0.5f, 0f,    1f, 1f, 1f, 1f,       0,0,
                            0.5f, 0.5f, 0f,     1f, 1f, 1f, 1f,        1,0,
                            -0.5f, -0.5f, 0f,   1f, 1f, 1f, 1f,         0,1},
                new int[]{2, 1, 0,0,1,3}
        ), "assets/apple.png");*/
/*
        t = new GraphicElement(new Vector3f(), new Vector3f(), new Vector3f(1f, 1f, 1f), new Mesh(
                new float[]{
                        -0.5f, 0.5f, 0f,    1f, 0f, 1f, 1f,
                        -0.5f, -0.5f, 0f,    0f, 1f, 1f, 1f,
                        0.5f, -0.5f, 0f,     1f, 1f, 0f, 1f,
                        0.5f, 0.5f, 0f,     0f, 0f, 1f, 1f
                },
                new int[]{0, 1, 3, 3, 1, 2}, Mesh.DIMENSION_3, Mesh.RGBA
        ), GL_TRIANGLES);
*/
        t = new GraphicElement(new Vector3f(), new Vector3f(), new Vector3f(1,1,1), new Mesh(
                new float[]{
                        // VO
                        -0.5f,  0.5f,  0.5f,    1f, 0f, 0f, 1f,
                        // V1
                        -0.5f, -0.5f,  0.5f,    0f, 1f, 0f, 1f,
                        // V2
                        0.5f, -0.5f,  0.5f,    0f, 0f, 1f, 1f,
                        // V3
                        0.5f,  0.5f,  0.5f,    1f, 1f, 0f, 1f,
                        // V4
                        -0.5f,  0.5f, -0.5f,    1f, 0f, 1f, 1f,
                        // V5
                        0.5f,  0.5f, -0.5f,    0f, 1f, 1f, 1f,
                                // V6
                        -0.5f, -0.5f, -0.5f,    0.5f, 0f, 0.5f, 1f,
                        // V7
                        0.5f, -0.5f, -0.5f,    0f, 0.5f, 0.5f, 1f
                },
                new int[]{
                        // Front face
                        0, 1, 3, 3, 1, 2,
                        // Top Face
                        4, 0, 3, 5, 4, 3,
                        // Right face
                        3, 2, 7, 5, 3, 7,
                        // Left face
                        6, 1, 0, 6, 0, 4,
                        // Bottom face
                        2, 1, 6, 2, 6, 7,
                        // Back face
                        7, 6, 4, 7, 4, 5,
                },
                Mesh.DIMENSION_3, Mesh.RGBA
        ), GL_TRIANGLES);

        addShape(t);

        System.out.println(primaryWindow.getSize());

       // addShape(gelem);
    }

    @Override
    protected void render(double dt)
    {
        var pos = Mouse.getPosition(getPrimaryWindow());

        getPrimaryWindow().clear();


        getShapeRenderer().render(t, camera);

        getPrimaryWindow().flip();
    }

    @Override
    protected void update(double dt)
    {
        if(Input.isLeftButtonPressed(getPrimaryWindow()))
        {
            t.setPosition(new Vector3f(camera.getPosition()));
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