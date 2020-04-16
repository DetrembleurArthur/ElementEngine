import game.jgengine.event.Input;
import game.jgengine.event.Mouse;
import game.jgengine.exceptions.SysException;
import game.jgengine.graphics.shaders.Shader;
import game.jgengine.graphics.shapes.*;
import game.jgengine.sys.Cursor;
import game.jgengine.sys.Game;
import game.jgengine.utils.Color;
import game.jgengine.utils.Colors;
import game.jgengine.utils.Vec2f;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_1;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_2;
import static org.lwjgl.system.MemoryUtil.memUTF8;

public class MyTestGame extends Game
{

    Shader shader;
    LineLoop line;

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

        shader = new Shader(
                "src/game/jgengine/graphics/shaders/vertex.glsl",
                "src/game/jgengine/graphics/shaders/fragment.glsl", true);
        addShader(shader);

        line = new LineLoop(new Vec2f[]
                {
                        new Vec2f(-0.2f, 0f),
                        new Vec2f(-0.2f, 0.2f),
                }, Colors.MAGENTA);
        line.setWidth(3);
        line.setGradient(Colors.RED, Colors.TRANSPARENT);
        addShape(line);
    }

    @Override
    protected void render(double dt)
    {
        getPrimaryWindow().clear();


        //Graphics.setColor(Colors.LIME);
        line.draw(shader);
/*
        glBegin(GL_LINE_STRIP_ADJACENCY);

        glVertex3f(-0.1f, 0f, 0f); glVertex3f(-0.2f, 0f, 0f);
        glVertex3f(-0.1f, 0.2f, 0f); glVertex3f(-0.2f, 0.2f, 0f);

        glEnd();
*/

        getPrimaryWindow().flip();
    }

    int i = 0;

    @Override
    protected void update(double dt)
    {
        getPrimaryWindow().setTitle("fps " + Double.toString(1.f /dt));
       // var pos = getPrimaryWindow().normal(Mouse.getPosition(getPrimaryWindow()));


    }

    @Override
    public void keyPressedEventHandler(int key)
    {
        //i = (i + 1) % line.getNbPoints();
        line.setGradient(Colors.RED, Colors.TRANSPARENT);
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
        if(Input.isButtonPressed(getPrimaryWindow(), GLFW_MOUSE_BUTTON_1))
        {
            var pos = getPrimaryWindow().normal(Mouse.getPosition(getPrimaryWindow()));
            line.addPoint(pos, Colors.ORANGE);

        }
        else
        {
            if(Input.isButtonPressed(getPrimaryWindow(), GLFW_MOUSE_BUTTON_2))
            {
                line.subPoint();
            }
        }
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
        getPrimaryWindow().updateViewport();
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