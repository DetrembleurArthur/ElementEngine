import game.jgengine.event.Input;
import game.jgengine.event.Mouse;
import game.jgengine.exceptions.SysException;
import game.jgengine.graphics.Renderer;
import game.jgengine.graphics.camera.*;
import game.jgengine.graphics.loaders.TextureLoader;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Game;
import game.jgengine.utils.Colors;
import org.joml.Vector3f;
import org.joml.Vector4f;

import static org.lwjgl.glfw.GLFW.*;


public class MyTestGame extends Game
{
    Rectangle gelem;

    Camera2D camera;
    Camera3D camera3D;

    Renderer renderer;


    boolean centered = false;

    @Override
    protected void load()
    {
        getPrimaryWindow().setClearColor(Colors.TURQUOISE);
        setFramerateLimit(60);
        getPrimaryWindow().setResizeable(true);
        getPrimaryWindow().center();


        TextureLoader.loadDir("assets/");
        gelem = new Rectangle(Registry.getTexture("bricks.png"));

        gelem.setDimension(200, 200);
        //gelem.setCenterOrigin();
        gelem.setOpacity(0.5f);
        renderer = new Renderer(Registry.getShader("DEFAULT"), getPrimaryWindow());



        camera = new Camera2D(new OrthoProjectionSettings(getPrimaryWindow()));
        camera3D = new Camera3D(new PerspProjectionSettings(70f, getPrimaryWindow()));


    }

    @Override
    protected void render(double dt)
    {
        var pos = Mouse.getPosition(getPrimaryWindow());

        getPrimaryWindow().clear();

        renderer.render(gelem, camera);

        getPrimaryWindow().flip();
    }

    @Override
    protected void update(double dt)
    {
        getPrimaryWindow().setTitle("fps " + Double.toString(1.f /dt));
        camera.activateKeys(getPrimaryWindow(), Camera2D.SPECTATOR_KEY_SET);
        var mp = Mouse.getPosition(getPrimaryWindow());
        gelem.setPosition(new Vector3f(mp.x, mp.y, 0));
        gelem.getRotation().z += 1;
    }

    @Override
    public void keyPressedEventHandler(int key)
    {
        if(Input.isKeyPressed(getPrimaryWindow(), GLFW_KEY_ENTER))
        {
            /*centered = !centered;
            if(centered)
            {
                camera3D.setOldMouse(Mouse.getPosition(getPrimaryWindow()));
                getPrimaryWindow().disableCursor();
            }
            else
                getPrimaryWindow().resetCursor();****/

        }
    }

    @Override
    public void cursorMovedEventHandler(double xpos, double ypos)
    {
        var pos = Mouse.getPosition(getPrimaryWindow());

        if(centered)
        {
            //camera3D.update(new Vector2f((float)xpos, (float)ypos));
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