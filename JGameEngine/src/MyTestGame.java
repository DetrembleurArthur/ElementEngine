import game.jgengine.event.Input;
import game.jgengine.event.Mouse;
import game.jgengine.exceptions.SysException;
import game.jgengine.entity.GameObject;
import game.jgengine.graphics.Renderer;
import game.jgengine.graphics.camera.*;
import game.jgengine.graphics.loaders.ObjData;
import game.jgengine.graphics.loaders.ObjLoader;
import game.jgengine.graphics.shaders.Texture;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Game;
import game.jgengine.utils.Colors;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;


public class MyTestGame extends Game
{
    GameObject gelem;

    Camera2D camera;
    Camera3D camera3D;

    Renderer renderer;


    boolean centered = false;

    @Override
    protected void load()
    {
        getPrimaryWindow().setClearColor(Colors.BLUE);
        setFramerateLimit(60);
        getPrimaryWindow().setResizeable(true);
        getPrimaryWindow().center();

        Texture texture = new Texture("assets/stone.jpg", false);
        Registry.set("brick", texture);

        ObjData objData = ObjLoader.loadModel("assets/3Dmodels/h4_carbine.obj");
        //objData.show();
        assert objData != null;
        gelem = new GameObject(objData.extractMesh(), null);
        gelem.setFillColor(Colors.TURQUOISE);
        gelem.getRotation().y += 90;
        renderer = new Renderer(Registry.getShader("DEFAULT"), getPrimaryWindow());



        camera = new Camera2D(new OrthoProjectionSettings(getPrimaryWindow()));
        camera3D = new Camera3D(new PerspProjectionSettings(70f, getPrimaryWindow()));
        camera3D.getPosition().z += 50;


    }

    @Override
    protected void render(double dt)
    {
        var pos = Mouse.getPosition(getPrimaryWindow());

        getPrimaryWindow().clear();

        renderer.render(gelem, camera3D);

        getPrimaryWindow().flip();
    }

    @Override
    protected void update(double dt)
    {
        getPrimaryWindow().setTitle("fps " + Double.toString(1.f /dt));
        var mp = Mouse.getPosition(getPrimaryWindow());
        camera3D.activateKeys(Camera3D.SPECTATOR_KEY_SET,
                getPrimaryWindow());
    }

    @Override
    public void keyPressedEventHandler(int key)
    {
        if(Input.isKeyPressed(getPrimaryWindow(), GLFW_KEY_ENTER))
        {
            centered = !centered;
            if(centered)
            {
                camera3D.setOldMouse(Mouse.getPosition(getPrimaryWindow()));
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
            camera3D.update(new Vector2f((float)xpos, (float)ypos));
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