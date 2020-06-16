import game.jgengine.debug.Logs;
import game.jgengine.event.Mouse;
import game.jgengine.exceptions.SysException;
import game.jgengine.graphics.Renderer;
import game.jgengine.graphics.camera.*;
import game.jgengine.graphics.loaders.TextureLoader;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.graphics.text.Font;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Game;
import game.jgengine.time.DynamicTimer;
import game.jgengine.time.StaticTimer;
import game.jgengine.time.SyncTimer;
import game.jgengine.tweening.*;
import game.jgengine.utils.Colors;
import game.jgengine.utils.MathUtil;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.Objects;


public class MyTestGame extends Game
{
    Rectangle gelem;

    Camera2D camera;
    Camera3D camera3D;

    Renderer renderer;

    TimedTweenAction tweenAction1;
    TimedTweenAction tweenAction2;
    float cptr = 0f;
    float cptr2 = 0f;

    SyncTimer staticTimer;
    boolean centered = false;


    @Override
    protected void load()
    {
        getPrimaryWindow().setClearColor(Colors.TURQUOISE);
        getPrimaryWindow().setSize(1800, 1000);
        setFramerateLimit(60);
        getPrimaryWindow().setResizeable(false);
        getPrimaryWindow().center();


        TextureLoader.loadDir("assets/");
        gelem = new Rectangle(Registry.getTexture("bricks.png"));

        gelem.setDimension(200, 200);

        //gelem.setCenterOrigin();
        gelem.setPosition(new Vector3f(900, 500, 0));
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
        var r = gelem.getRotation();
        gelem.setRotation(0);
        gelem.setOpacity(0.3f);
        renderer.render(gelem, camera);
        gelem.setRotation(r);
        gelem.setOpacity(1f);


        getPrimaryWindow().flip();
    }

    @Override
    protected void update(double dt)
    {
        getPrimaryWindow().setTitle("fps " + Double.toString(1.f /dt));
        camera.activateKeys(getPrimaryWindow(), Camera2D.SPECTATOR_KEY_SET);
        var mp = Mouse.getPosition();
        //gelem.setPosition(new Vector3f(mp.x, mp.y, 0));
        gelem.getRotation().z += 0.5f;
        //gelem.setPosition(MathUtil.rotateAround(gelem.getPosition2D(), Mouse.getPosition(camera), (float) (60*dt)));
        if(gelem.contains(Mouse.getPosition(camera)))
            gelem.setFillColor(Colors.RED);
        else
            gelem.setFillColor(Colors.GREEN);
        Logs.print(Mouse.getPosition(camera).x + " ");
        gelem.getPosition().x = TweenAction.get(TweenFunctions.EASE_IN_OUT_CUBIC, 0, 1800, Mouse.getPosition(camera).x);
        gelem.getPosition().y = TweenAction.get(TweenFunctions.EASE_IN_OUT_CUBIC, 0, 1000, Mouse.getPosition(camera).y);
    }


    @Override
    public void windowCloseEventHandler()
    {

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