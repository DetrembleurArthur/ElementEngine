package com.elemengine.demo;


import com.elemengine.debug.Log;
import com.elemengine.event.Mouse;
import com.elemengine.event.handler.annotations.OnEvent;
import com.elemengine.graphics.camera.Camera2D;
import com.elemengine.graphics.loaders.TextureLoader;
import com.elemengine.graphics.rendering.SpriteSheet;
import com.elemengine.graphics.shapes.Rectangle;
import com.elemengine.registry.Registry;
import com.elemengine.scripting.CScript;
import com.elemengine.scripting.Script;
import com.elemengine.sys.Scene2D;
import com.elemengine.sys.Window;
import com.elemengine.time.AsyncTimer;
import com.elemengine.time.DynamicTimer;
import com.elemengine.time.SyncTimer;
import com.elemengine.utils.Colors;
import com.elemengine.utils.DynamicLaterList;
import com.elemengine.utils.LaterList;
import com.elemengine.utils.MathUtil;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class MainScene extends Scene2D
{



    private DynamicLaterList slimes = new DynamicLaterList();
    private DynamicLaterList frags = new DynamicLaterList();
    private SpriteSheet spriteSheet;
    private Rectangle focusElement;

    public Rectangle createSlime()
    {
        Rectangle rectangle = new Rectangle();
        float number = MathUtil.rand(0.6f, 0.2f);
        rectangle.setSize(80 * number, 80 * number);
        rectangle.sprites_c().addSpriteSheet("blob-idle", spriteSheet);
        rectangle.sprites_c().setCurrent("blob-idle");
        rectangle.sprites_c().setCurrentId(0);
        rectangle.sprites_c().setSpeedAnimation(MathUtil.rand(1000, 250));
        rectangle.timers_c();
        rectangle.moves_c();
        rectangle.setFillColor(Colors.random());
        rectangle.setCenterOrigin();
        rectangle.setCenterPosition(Window.WINDOW.getCenter());
        rectangle.events_c().onMouseButtonClicked(sender -> {
            focusElement = rectangle;
        }, false);
        rectangle.timers_c().add(
                new SyncTimer(1500, DynamicTimer.INFINITE, () -> {
                    var campos = Window.WINDOW.getCenter();

                    var newpos = new Vector2f(MathUtil.rand(campos.x + 600, campos.x - 600), MathUtil.rand(campos.y + 600, campos.y - 600));
                    rectangle.moves_c().speedToward(
                            newpos
                            ,
                            new Vector2f(300, 300));
                }));
        return rectangle;
    }

    @Override
    public void load()
    {



        Window.WINDOW.setClearColor(Colors.ORANGE);
        for(int i = 0; i < 15; i++)
            slimes.addLater(createSlime());
    }


    @Override
    public void loadResources()
    {
        TextureLoader.loadDir("src/main/resources/sprites");
        spriteSheet = new SpriteSheet(Registry.getTexture("blob.png"), 2, 2, 3);


    }

    @Override
    public void update(double dt)
    {
        getCamera2d().activateKeys(Window.WINDOW, Camera2D.SPECTATOR_KEY_SET);
        slimes.run();
        frags.run();

        if(focusElement != null)
            getCamera2d().focus(focusElement.getCenterPosition());

    }

    @Override
    public void render(double dt)
    {
        slimes.draw(getDefaultRenderer());
        frags.draw(getDefaultRenderer());
    }

    @Override
    public void close()
    {
        slimes.destroy();
        frags.destroy();
    }

    @Override
    public void closeResources()
    {

    }

    @OnEvent(OnEvent.Types.SCROLL)
    public void scroll(double x, double y)
    {
        getCamera2d().setZoom(getCamera2d().getZoom().x * (y >= 1 ? 1.3f : 0.7f));
    }

    @OnEvent(OnEvent.Types.KEY_PRESSED)
    public void keyPressedEventHandler(int key)
    {
        if (key == GLFW.GLFW_KEY_LEFT_CONTROL)
        {
            Window.WINDOW.takeScreenShot();
        } else if (key == GLFW.GLFW_KEY_SPACE)
        {
            if(slimes.size() == 0) return;
            Rectangle slime = (Rectangle) slimes.get(0);
            slime.setTexture(null);
            var list = slime.getFragments(6, 6);
            var color = slime.getFillColor();
            list.forEach(triangle -> {
                triangle.moves_c().setSpeed(new Vector2f(MathUtil.rand(150, -150), MathUtil.rand(150, -150)));
                triangle.moves_c().setRotationSpeed(100);
                triangle.setFillColor(color);
                triangle.timers_c().add(new SyncTimer(3000, 1, () -> frags.removeLater(triangle)));
            });
            frags.addLater(list);
            slimes.removeLater(slime);
        }
    }

    @OnEvent(OnEvent.Types.BUTTON_PRESSED)
    public void buttonPressedEventHandler(int button)
    {
        if (button == Mouse.Button.RIGHT)
            getCamera2d().focus(Mouse.getPosition(getCamera2d()));
        else if (button == Mouse.Button.LEFT)
            slimes.addLater(createSlime());
    }
}
