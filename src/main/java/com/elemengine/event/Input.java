package com.elemengine.event;

import com.elemengine.debug.Log;
import com.elemengine.sys.Window;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFWGamepadState;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.glfw.GLFW.*;

public class Input
{
    public static boolean isKeyPressed(Window window, int keycode)
    {
        return glfwGetKey(window.getId(), keycode) == 1;
    }

    public static boolean isButtonPressed(Window window, int buttonCode)
    {
        return glfwGetMouseButton(window.getId(), buttonCode) == 1;
    }

    public static boolean isButtonPressed(int buttonCode)
    {
        return glfwGetMouseButton(Window.WINDOW.getId(), buttonCode) == 1;
    }

    public static boolean isButtonPressed()
    {
        long wid = Window.WINDOW.getId();
        for (int i = 0; i < 8; i++)
        {
            if (glfwGetMouseButton(wid, i) == 1)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isLeftButtonPressed(Window window)
    {
        return glfwGetMouseButton(window.getId(), GLFW_MOUSE_BUTTON_1) == 1;
    }

    public static boolean isRightButtonPressed(Window window)
    {
        return glfwGetMouseButton(window.getId(), GLFW_MOUSE_BUTTON_2) == 1;
    }

    public static boolean isMiddleButtonPressed(Window window)
    {
        return glfwGetMouseButton(window.getId(), GLFW_MOUSE_BUTTON_3) == 1;
    }

    public static String getKeyName(int keycode)
    {
        return glfwGetKeyName(keycode, 0);
    }

    public static boolean isJoystickPresent(int joystickId)
    {
        return glfwJoystickPresent(joystickId);
    }

    public static Vector2f getJoystickAxes(int joystickId, int joystick, int sensitivity)
    {
        FloatBuffer buffer = glfwGetJoystickAxes(joystickId);
        Vector2f axes = new Vector2f((int) (buffer.get(joystick) * sensitivity), (int) (buffer.get(joystick + 1) * sensitivity)).div(sensitivity);
        buffer.clear();
        return axes;
    }

	public static Vector2f getJoystickAxes(int joystickId, int joystick)
	{
		return getJoystickAxes(joystickId, joystick,10);
	}

    public static class Joystick
    {
        public static final int LEFT = 0;
        public static final int RIGHT = 2;
        public static final int LEFT_TRIGGER = 4;
        public static final int RIGHT_TRIGGER = 5;
        public static final int LEFT_RIGHT_TRIGGER = 6;
    }

    public static boolean isControllerTriggerPressed(int joystickId, int trigger)
    {
        FloatBuffer buffer = glfwGetJoystickAxes(joystickId);
        boolean pressed = false;
        if(trigger == Joystick.LEFT_TRIGGER || trigger == Joystick.RIGHT_TRIGGER)
        {
            pressed = buffer.get(trigger) >= 1;
        }
        else if(trigger == Joystick.LEFT_RIGHT_TRIGGER)
        {
            pressed = buffer.get(Joystick.LEFT_TRIGGER) >= 1 && buffer.get(Joystick.RIGHT_TRIGGER) >= 1;
        }
        buffer.clear();
        return pressed;
    }

	public static boolean isControllerButtonPressed(int joystickId, int btn)
	{
		ByteBuffer buffer = glfwGetJoystickButtons(joystickId);
		boolean pressed = buffer.get(btn) == GLFW_PRESS;
		buffer.clear();
		return pressed;
	}

	public static boolean isControllerButtonReleased(int joystickId, int btn)
	{
		ByteBuffer buffer = glfwGetJoystickButtons(joystickId);
		boolean pressed = buffer.get(btn) == GLFW_RELEASE;
		buffer.clear();
		return pressed;
	}


	public static int getDirectionalHats(int joystickId)
	{
		ByteBuffer buffer = glfwGetJoystickHats(joystickId);
		int hat = buffer.get(0);
		buffer.clear();
		return hat;
	}

	public static String getControllerName(int joystickId)
	{
		return glfwGetJoystickName(joystickId);
	}

	public static class Xbox
	{
		public static final int A = 0;
		public static final int B = 1;
		public static final int X = 2;
		public static final int Y = 3;
		public static final int LB = 4;
		public static final int RB = 5;
		public static final int SELECT = 6;
		public static final int START = 7;
		public static final int L = 8;
		public static final int R = 9;
		public static final int UP = 10;
		public static final int RIGHT = 11;
		public static final int DOWN = 12;
		public static final int LEFT = 13;
	}
}
