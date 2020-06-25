import game.jgengine.debug.Logs;
import game.jgengine.event.Input;
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
import game.jgengine.graphics.texts.FontSet;
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
import org.lwjgl.glfw.GLFW;

import java.util.Calendar;


public class MyTestGame extends Game
{

    Camera2D camera;
    Camera3D camera3D;
    Renderer renderer;

    Font font;
    Text text;

    Circle circle;
    Rectangle rect;

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





        //text.setLineStripRenderMode();
        //text.setTexture(null);

        font = new Font("assets/fonts/arial.fnt");

        text = new Text(font, "FPS: ");
        //text.getTexture().enableLinear();
        text.setSizePx(30);
        text.setTopLeftOrigin();
        text.setPosition(new Vector2f(20, 20));
        text.setFillColor(Colors.BLACK);


        circle = new Circle(100, 330, null);

        //circle.setRotation(45);
        circle.setCenterOrigin();
        circle.setPosition(getPrimaryWindow().getCenter());
        rect = circle.getBoundingBox().asRectangle();
        //rect.rotateAround(circle.getPosition2D(), 45);
       // rect.rotate(45);
        rect.setFillColor(new Vector4f(1,1,0,0.5f));
    }

    @Override
    protected void render(double dt)
    {
        getPrimaryWindow().clear();



        renderer.render(circle, camera);

        renderer.render(text, camera);
        renderer.render(rect, camera);


        getPrimaryWindow().flip();
    }

    @Override
    protected void update(double dt)
    {
        //getPrimaryWindow().setTitle("fps " + Double.toString(1.f /dt));
        text.setText("FPS: " + (int)(1f / dt));

        camera.activateKeys(getPrimaryWindow(), Camera2D.SPECTATOR_KEY_SET);
        var mp = Mouse.getPosition(camera);
        text.setPosition(camera.getPosition());
        if(circle.isClosed(mp))
            circle.setFillColor(Colors.GREEN);
        else
            circle.setFillColor(Colors.RED);
    }




    @Override
    public void buttonPressedEventHandler(int button)
    {
    }

    @Override
    public void keyReleasedEventHandler(int key)
    {
        Logs.print(key + " " + GLFW.GLFW_KEY_ENTER);
        if(key == GLFW.GLFW_KEY_ENTER)
            getPrimaryWindow().takeScreenShot("assets/screen.png");
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