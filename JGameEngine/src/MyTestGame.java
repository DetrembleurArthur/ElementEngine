import game.jgengine.event.Input;
import game.jgengine.event.Mouse;
import game.jgengine.exceptions.SysException;
import game.jgengine.graphics.Renderer;
import game.jgengine.graphics.camera.*;
import game.jgengine.graphics.loaders.TextureLoader;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Game;
import game.jgengine.tweening.TimedTweenAction;
import game.jgengine.tweening.TweenAction;
import game.jgengine.tweening.TweenObject;
import game.jgengine.tweening.TweenFunctions;
import game.jgengine.utils.Colors;

import static org.lwjgl.glfw.GLFW.*;


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

    boolean centered = false;

    @Override
    protected void load()
    {
        getPrimaryWindow().setClearColor(Colors.BLACK);
        getPrimaryWindow().setSize(1800, 1000);
        setFramerateLimit(60);
        getPrimaryWindow().setResizeable(true);
        getPrimaryWindow().center();


        TextureLoader.loadDir("assets/");
        gelem = new Rectangle(Registry.getTexture("bricks.png"));

        gelem.setDimension(200, 200);
        gelem.setCenterOrigin();
        renderer = new Renderer(Registry.getShader("DEFAULT"), getPrimaryWindow());



        camera = new Camera2D(new OrthoProjectionSettings(getPrimaryWindow()));
        camera3D = new Camera3D(new PerspProjectionSettings(70f, getPrimaryWindow()));

        tweenAction1 = new TimedTweenAction(new TweenObject(0, 1200, TweenFunctions.EASE_IN_CIRC),
                (v) -> { gelem.getPosition().x = v;},
                1000);
        tweenAction2 = new TimedTweenAction(new TweenObject(0, 600, TweenFunctions.EASE_OUT_CIRC),
                (v) -> { gelem.getPosition().y = v;},
                1000);

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
        //
        // gelem.setPosition(new Vector3f(mp.x, mp.y, 0));

        tweenAction1.run();
        tweenAction2.run();
        cptr += 0.005f;
        cptr2 += 0.005f;


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
        cptr = 0.0f;
        cptr2 = 0.0f;
        var mpos = Mouse.getPosition(getPrimaryWindow());
        var pos = gelem.getPosition();
        tweenAction1.start();
        tweenAction2.start();
        tweenAction1.getTweenObject().setStartValue(pos.x);
        tweenAction1.getTweenObject().setEndValue(mpos.x);
        tweenAction2.getTweenObject().setStartValue(pos.y);
        tweenAction2.getTweenObject().setEndValue(mpos.y);

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