import game.jgengine.debug.Logs;
import game.jgengine.event.Mouse;
import game.jgengine.exceptions.SysException;
import game.jgengine.graphics.Renderer;
import game.jgengine.graphics.camera.*;
import game.jgengine.graphics.loaders.TextureLoader;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Game;
import game.jgengine.time.DynamicTimer;
import game.jgengine.time.StaticTimer;
import game.jgengine.time.SyncTimer;
import game.jgengine.tweening.*;
import game.jgengine.utils.Colors;
import org.joml.Vector3f;


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

        gelem.setCenterOrigin();
        gelem.setPosition(new Vector3f(900, 500, 0));
        renderer = new Renderer(Registry.getShader("DEFAULT"), getPrimaryWindow());



        camera = new Camera2D(new OrthoProjectionSettings(getPrimaryWindow()));
        camera3D = new Camera3D(new PerspProjectionSettings(70f, getPrimaryWindow()));


        tweenAction1 = new TimedTweenAction(
                0,
                360,
                TweenFunctions.LINEAR,
                (x) -> {gelem.getRotation().z = x;},
                6000,
                TimedTweenAction.INFINITE_CYCLE,
                true);
        //tweenAction2 = new TimedTweenAction(0, 300, TweenFunctions.EASE_IN_OUT_QUART, (x) -> {gelem.getPosition().y = x;}, 3000, 1, true);
        staticTimer = new SyncTimer(500, DynamicTimer.INFINITE, () -> { gelem.getRotation().z += 30;});
        staticTimer.setContinuousTrigger(false);

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
        var mp = Mouse.getPosition();

        staticTimer.run();
    }


    @Override
    public void windowCloseEventHandler()
    {

    }

    @Override
    public void buttonPressedEventHandler(int button)
    {
        staticTimer.activate();


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