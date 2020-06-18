import game.jgengine.debug.Logs;
import game.jgengine.event.Input;
import game.jgengine.event.Mouse;
import game.jgengine.exceptions.SysException;
import game.jgengine.graphics.Renderer;
import game.jgengine.graphics.camera.*;
import game.jgengine.graphics.loaders.TextureLoader;
import game.jgengine.graphics.shapes.Circle;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.graphics.shapes.SpriteSheet;
import game.jgengine.graphics.text.Font;
import game.jgengine.graphics.text.Text;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Game;
import game.jgengine.time.DynamicTimer;
import game.jgengine.time.StaticTimer;
import game.jgengine.time.SyncTimer;
import game.jgengine.tweening.*;
import game.jgengine.utils.Colors;
import game.jgengine.utils.MathUtil;
import org.joml.Circled;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import java.util.Objects;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_LINE_LOOP;


public class MyTestGame extends Game
{

    Camera2D camera;
    Camera3D camera3D;

    Renderer renderer;
    boolean centered = false;

    Font font;
    Text text;


    @Override
    protected void load()
    {
        getPrimaryWindow().setClearColor(Colors.WHITE);
        getPrimaryWindow().setSize(1800, 1000);
        setFramerateLimit(60);
        getPrimaryWindow().setResizeable(false);
        getPrimaryWindow().center();


        TextureLoader.loadDir("assets/");



        renderer = new Renderer(Registry.getShader("DEFAULT"), getPrimaryWindow());



        camera = new Camera2D(new OrthoProjectionSettings(getPrimaryWindow()));
        camera3D = new Camera3D(new PerspProjectionSettings(70f, getPrimaryWindow()));

        font = new Font("assets/fonts/test.fnt");
        text = new Text(font, "Hello world!");
        text.setFillColor(Colors.RED);

       // text.setLineLoopRenderMode();
       // text.setTexture(null);


    }

    @Override
    protected void render(double dt)
    {
        var pos = Mouse.getPosition(getPrimaryWindow());
        getPrimaryWindow().clear();


        renderer.render(text, camera);


        getPrimaryWindow().flip();
    }

    @Override
    protected void update(double dt)
    {
        getPrimaryWindow().setTitle("fps " + Double.toString(1.f /dt));
        camera.activateKeys(getPrimaryWindow(), Camera2D.SPECTATOR_KEY_SET);
        var mp = Mouse.getPosition();
        text.setPosition(mp);
    }




    @Override
    public void buttonPressedEventHandler(int button)
    {

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