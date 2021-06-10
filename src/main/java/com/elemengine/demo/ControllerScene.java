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
import com.elemengine.utils.Colors;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

public class ControllerScene extends Scene2D
{
    Rectangle slime;
    SpriteSheet spriteSheet;

    @Override
    public void load()
    {
        getCamera2d().setSpeed(new Vector2f(50, 50));

        spriteSheet = new SpriteSheet(Registry.getTexture("blob.png"), 2, 2, 3);

        slime = new Rectangle();
        slime.setFillColor(Colors.LIME);
        slime.setCenterOrigin();
        slime.setPosition(Window.WINDOW.getCenter());
        slime.setSize(80, 80);
        slime.sprites_c().addSpriteSheet("blob-idle", spriteSheet);
        slime.sprites_c().setCurrent("blob-idle");
        slime.sprites_c().setCurrentId(0);
        slime.sprites_c().setSpeedAnimation(300);
    }

    @Override
    public void update(double dt)
    {

        getCamera2d().activateKeys(Window.WINDOW, Camera2D.SPECTATOR_KEY_SET);
        slime.run();
        var axes = Input.getJoystickAxes(GLFW.GLFW_JOYSTICK_1, Input.Joystick.LEFT);

        slime.move(axes.x * 15, axes.y * 15);

        if(Input.isControllerTriggerPressed(GLFW.GLFW_JOYSTICK_1, Input.Joystick.LEFT_TRIGGER))
        {
            getCamera2d().zoom(1.01f);
        }
        else if(Input.isControllerTriggerPressed(GLFW.GLFW_JOYSTICK_1, Input.Joystick.RIGHT_TRIGGER))
        {
            getCamera2d().zoom(0.99f);
        }
    }

    @Override
    public void render(double dt)
    {
        slime.draw(getDefaultRenderer());
    }

    @Override
    public void close()
    {
        slime.destroy();
    }

    @Override
    public void loadResources()
    {

    }

    @Override
    public void closeResources()
    {

    }

    @OnEvent(OnEvent.Types.KEY_RELEASED)
    public void space(int btn)
    {
        if(btn == GLFW.GLFW_KEY_SPACE)
        {
            Log.print(Input.isJoystickPresent(GLFW.GLFW_JOYSTICK_1));
        }
    }

    @OnEvent(OnEvent.Types.JOYSTICK_CONNECTED)
    public void connected(int i, int e)
    {
        Log.print("CONNECTED");
    }
}
