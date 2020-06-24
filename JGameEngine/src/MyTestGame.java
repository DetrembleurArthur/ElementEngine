import game.jgengine.debug.Logs;
import game.jgengine.event.Mouse;
import game.jgengine.exceptions.SysException;
import game.jgengine.graphics.rendering.*;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.camera.Camera3D;
import game.jgengine.graphics.camera.OrthoProjectionSettings;
import game.jgengine.graphics.camera.PerspProjectionSettings;
import game.jgengine.graphics.loaders.TextureLoader;
import game.jgengine.graphics.shapes.Circle;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.graphics.texts.Font;
import game.jgengine.graphics.texts.Text;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Game;
import game.jgengine.tweening.TimedTweenAction;
import game.jgengine.tweening.TweenFunctions;
import game.jgengine.utils.Colors;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;
import org.joml.Vector4f;


public class MyTestGame extends Game
{

    Camera2D camera;
    Camera3D camera3D;

    Renderer renderer;
    boolean centered = false;

    Font font;
    Text text;

    Vector2f speed;

    Rectangle rect;
    Circle circle;



    TimedTweenAction a2;
    TimedTweenAction a1;

    @Override
    protected void load()
    {
        getPrimaryWindow().setClearColor(new Vector4f(0, 0.8f, 0.8f, 1));
        getPrimaryWindow().setSize(1800, 1000);
        getPrimaryWindow().maintainSizeRatio(true);
        setFramerateLimit(60);
        getPrimaryWindow().setResizeable(true);
        getPrimaryWindow().center();



        TextureLoader.loadDir("assets/");





        renderer = new Renderer(Registry.getShader("DEFAULT"), getPrimaryWindow());



        camera = new Camera2D(new OrthoProjectionSettings(getPrimaryWindow()));
        camera3D = new Camera3D(new PerspProjectionSettings(70f, getPrimaryWindow()));

        font = new Font("assets/fonts/gotic.fnt");

        Registry.set("test", font);



        //text.setLineStripRenderMode();
        //text.setTexture(null);


        rect = new Rectangle(null);
        rect.setSize(50, 100);
        rect.setCenterOrigin();
        rect.setPosition(getPrimaryWindow().getCenter());
        speed = rect.getComponent(getPrimaryWindow().getCenter());
        rect.setFillColor(Colors.MAGENTA);

        text = new Text(Registry.getFont("test"), "Hello World!");

        text.setFillColor(Colors.RED);
        text.setSizePx(50);
        text.setCenterOrigin();
        text.setPosition(new Vector2f(200, 200));

        circle = new Circle(50, 30, null);
        circle.setSize(100, 75);
        circle.setPosition(new Vector2f(600, 600));
        circle.setFillColor(Colors.LIME);
        circle.setCenterOrigin();


        a1 = new TimedTweenAction(15, 100, TweenFunctions.EASE_IN_OUT_CUBIC,
                (x) -> {circle.setSize(x, circle.getSize().y);}
                , 2000, TimedTweenAction.INFINITE_CYCLE, true);
        a2 = new TimedTweenAction(15, 100, TweenFunctions.EASE_OUT_ELASTIC,
                (x) -> {circle.setSize(circle.getSize().y, x);}
                , 2000, TimedTweenAction.INFINITE_CYCLE, true);

        a1.start();
        a2.start();
    }

    @Override
    protected void render(double dt)
    {
        getPrimaryWindow().clear();



        renderer.render(text, camera);
        renderer.render(circle, camera);
        renderer.render(rect, camera);



        getPrimaryWindow().flip();
    }

    @Override
    protected void update(double dt)
    {
        getPrimaryWindow().setTitle("fps " + Double.toString(1.f /dt));
        camera.activateKeys(getPrimaryWindow(), Camera2D.SPECTATOR_KEY_SET);
        var mp = Mouse.getPosition(camera);
        circle.setPosition(mp);
        a1.run();
        a2.run();
    }




    @Override
    public void buttonPressedEventHandler(int button)
    {
        //getPrimaryWindow().takeScreenShot("assets/screen.png");
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