package com.elemengine.demo;

import com.elemengine.debug.Log;
import com.elemengine.event.Input;
import com.elemengine.event.handler.annotations.OnEvent;
import com.elemengine.graphics.camera.Camera2D;
import com.elemengine.graphics.rendering.SpriteSheet;
import com.elemengine.graphics.shapes.Rectangle;
import com.elemengine.registry.Registry;
import com.elemengine.sys.Scene2D;
import com.elemengine.sys.Window;
import com.elemengine.time.DynamicTimer;
import com.elemengine.time.SyncTimer;
import com.elemengine.utils.Colors;
import com.elemengine.utils.MathUtil;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

public class ControllerScene extends Scene2D
{
    Rectangle slime;
    SpriteSheet spriteSheet;
    int i = 0;

    DynamicTimer timer;


    @Override
    public void load()
    {
        timer = new SyncTimer(30, 1, () -> Log.print("end"));
        timer.setPeriodCounter(1);

        getCamera2d().setSpeed(new Vector2f(50, 50));

        spriteSheet = new SpriteSheet(Registry.getTexture("blob.png"), 2, 2, 3);

        slime = new Rectangle();
        slime.setFillColor(Colors.LIME);
        slime.setCenterOrigin();
        slime.setPosition(Window.WINDOW.get2DWorldCenter(getCamera2d()));
        slime.setSize(120, 120);
        slime.sprites_c().addSpriteSheet("blob-idle", spriteSheet);
        slime.sprites_c().setCurrent("blob-idle");
        slime.sprites_c().setCurrentId(0);
        slime.sprites_c().setSpeedAnimation(300);
        slime.events_c().onMouseEntered(sender -> slime.setFillColor(Colors.CYAN), getCamera2d());
        slime.events_c().onMouseExited(sender -> slime.setFillColor(Colors.LIME), getCamera2d());


        getLayoutMap().create("player", 0, defaultRenderer).put("player", slime);
        getLayoutMap().create("tears", 1, defaultRenderer);


        activeArrow();
    }

    @Override
    public void update(double dt)
    {
        getCamera2d().activateKeys(Window.WINDOW, Camera2D.SPECTATOR_KEY_SET);

        var axes = Input.getJoystickAxes(GLFW.GLFW_JOYSTICK_1, Input.Joystick.LEFT);
        var tearAxes = Input.getJoystickAxes(GLFW.GLFW_JOYSTICK_1, Input.Joystick.RIGHT);


        slime.movedt(axes.x * 600, axes.y * 600);


        timer.run();

        if (tearAxes.x + tearAxes.y != 0)
        {
            if(timer.isFinished())
            {
                Rectangle tear = new Rectangle(Registry.getTexture("tear.png"));
                tear.setFillColor(Colors.RED);
                tear.setSize(60, 60);
                tear.setCenterOrigin();
                var c= slime.getAngleVectorComponent(MathUtil.radianVectorToDegreeAngle(tearAxes));
                tear.placeAround(slime.getCenterPosition(), slime.getWidth(), c);
                tear.moves_c().setSpeed(
                        new Vector2f(800)
                                .mul(c)
                                .add(axes.x * 150, axes.y * 150)
                                .add(MathUtil.randV2f(150, -150)));
                tear.timers_c().add(new SyncTimer(2000, 1, () -> getLayoutMap().removeDynamic(tear, "tears")));
                getLayoutMap().put("tears", tear);
                timer.resetTime();
            }

        }




    }

    @Override
    public void render(double dt)
    {

    }

    @Override
    public void close()
    {

    }

    @OnEvent(OnEvent.Types.KEY_RELEASED)
    public void space(int btn)
    {
        if (btn == GLFW.GLFW_KEY_SPACE)
        {
            getLayoutMap().swap("player", "GUI");
        }
    }

    @OnEvent(OnEvent.Types.JOYSTICK_CONNECTED)
    public void connected(int i, int e)
    {
        Log.print("CONNECTED");
    }
}
