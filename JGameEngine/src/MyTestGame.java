import game.jgengine.debug.Logs;
import game.jgengine.event.Mouse;
import game.jgengine.exceptions.SysException;
import game.jgengine.graphics.rendering.*;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.camera.Camera3D;
import game.jgengine.graphics.camera.OrthoProjectionSettings;
import game.jgengine.graphics.camera.PerspProjectionSettings;
import game.jgengine.graphics.loaders.TextureLoader;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.graphics.texts.Font;
import game.jgengine.graphics.texts.Text;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Game;
import game.jgengine.utils.Colors;
import org.joml.Vector2f;
import org.joml.Vector2i;


public class MyTestGame extends Game
{

    Camera2D camera;
    Camera3D camera3D;

    Renderer renderer;
    boolean centered = false;

    Font font;
    Text text;

    Rectangle rect;


    TargetRenderer targetRenderer;
    TargetTexture target;
    Rectangle targetRect;

    @Override
    protected void load()
    {
        Logs.print("test");
        getPrimaryWindow().setClearColor(Colors.TURQUOISE);
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
        text.setSizePx(50);
        text.setPosition(new Vector2f(100, 100));
        //text.setScale(new Vector3f(60, 60, 60));

        //text.setLineStripRenderMode();
        //text.setTexture(null);

        rect = text.getBoundingBox().asRectangle();
        rect.setFillColor(Colors.BLUE);

        target = new TargetTexture(new Vector2i(300, 300));
        targetRect = new Rectangle(new Image("assets/bricks.png").toTexture(true));
        targetRect.setPosition(new Vector2f(600, 600));
        targetRect.setDimension(300, 300);
        targetRenderer = new TargetRenderer(Registry.getShader("DEFAULT"), getPrimaryWindow(), target);


    }

    @Override
    protected void render(double dt)
    {
        var pos = Mouse.getPosition(getPrimaryWindow());
        getPrimaryWindow().clear();


        renderer.render(rect, camera);
        renderer.render(text, camera);
        renderer.render(targetRect, camera);

        getPrimaryWindow().flip();
    }

    @Override
    protected void update(double dt)
    {
        getPrimaryWindow().setTitle("fps " + Double.toString(1.f /dt));
        camera.activateKeys(getPrimaryWindow(), Camera2D.SPECTATOR_KEY_SET);
        var mp = Mouse.getPosition(camera);

        if(text.getCharBox(0).isCollision(mp))
            text.setFillColor(Colors.GREEN);
        else
            text.setFillColor(Colors.RED);
    }




    @Override
    public void buttonPressedEventHandler(int button)
    {
        //targetRenderer.render(rect, camera);
        //targetRenderer.render(text, camera);
        Image.takeScreenShot("assets/screen.png");
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