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




    @Override
    protected void load()
    {
        getPrimaryWindow().setClearColor(new Vector4f(0, 0.8f, 0.8f, 1));
        getPrimaryWindow().setSize(1800, 1000);
        setFramerateLimit(60);
        getPrimaryWindow().setResizeable(true);
        getPrimaryWindow().center();
        getPrimaryWindow().maintainSizeRatio(false);



        TextureLoader.loadDir("assets/");





        renderer = new Renderer(Registry.getShader("DEFAULT"), getPrimaryWindow());



        camera = new Camera2D(new OrthoProjectionSettings(getPrimaryWindow()));
        camera3D = new Camera3D(new PerspProjectionSettings(70f, getPrimaryWindow()));

        font = new Font("assets/fonts/gotic.fnt");

        Registry.set("test", font);



        //text.setLineStripRenderMode();
        //text.setTexture(null);


        rect = new Rectangle(null);
        rect.setDimension(50, 100);
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
        circle.setPosition(new Vector2f(600, 600));
        circle.setFillColor(Colors.LIME);
        circle.setCenterOrigin();


    }

    @Override
    protected void render(double dt)
    {
        var pos = Mouse.getPosition(getPrimaryWindow());
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
    }




    @Override
    public void buttonPressedEventHandler(int button)
    {
        //getPrimaryWindow().takeScreenShot("assets/screen.png");
    }

    @Override
    public void windowResizedEventHandler(int width, int height)
    {

        getPrimaryWindow().maintainSizeRatio(true);
        getPrimaryWindow().aspectRatioUpdateViewport(width, height);
       if(camera == null) return;

       //camera.getOrthoProjSettings().update(getPrimaryWindow());

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