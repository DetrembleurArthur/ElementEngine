package com.elemengine.event.handler;

import com.elemengine.debug.Log;
import com.elemengine.event.handler.annotations.OnEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class EventCollector implements EventHandler
{
    private final Object target;
    private final HashMap<OnEvent.Types, ArrayList<Method>> eventMethods;

    public EventCollector(Object target)
    {
        this.target = target;
        eventMethods = new HashMap<>();
        init();
    }


    private void init()
    {
        for(Method method : target.getClass().getMethods())
        {
            if(method.isAnnotationPresent(OnEvent.class))
            {
                OnEvent annotation = method.getAnnotation(OnEvent.class);
                Log.print(annotation.value());
                if(!eventMethods.containsKey(annotation.value()))
                    eventMethods.put(annotation.value(), new ArrayList<>());
                eventMethods.get(annotation.value()).add(method);
            }
        }
    }

    private void trigger(OnEvent.Types eventType, Object ... args)
    {
        if(eventMethods.containsKey(eventType))
        {
            for(Method method : eventMethods.get(eventType))
            {
                try
                {
                    method.invoke(target, args);
                } catch (IllegalAccessException | InvocationTargetException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void buttonPressedEventHandler(int button)
    {
        trigger(OnEvent.Types.BUTTON_PRESSED, button);
    }

    @Override
    public void buttonReleasedEventHandler(int button)
    {
        trigger(OnEvent.Types.BUTTON_RELEASED, button);
    }

    @Override
    public void buttonRepeatedEventHandler(int button)
    {
        trigger(OnEvent.Types.BUTTON_REPEATED, button);
    }

    @Override
    public void cursorEnteredEventHandler()
    {
        trigger(OnEvent.Types.CURSOR_ENTERED);
    }

    @Override
    public void cursorExitedEventHandler()
    {
        trigger(OnEvent.Types.CURSOR_EXITED);
    }

    @Override
    public void cursorMovedEventHandler(double xpos, double ypos)
    {
        trigger(OnEvent.Types.CURSOR_MOVED, xpos, ypos);
    }

    @Override
    public void dropEventHandler(String[] items)
    {
        trigger(OnEvent.Types.DROP, (Object) items);
    }

    @Override
    public void keyPressedEventHandler(int key)
    {
        trigger(OnEvent.Types.KEY_PRESSED, key);
    }

    @Override
    public void keyReleasedEventHandler(int key)
    {
        trigger(OnEvent.Types.KEY_RELEASED, key);
    }

    @Override
    public void keyRepeatedEventHandler(int key)
    {
        trigger(OnEvent.Types.KEY_REPEATED, key);
    }

    @Override
    public void scrollEventHandler(double xoffset, double yoffset)
    {
        trigger(OnEvent.Types.SCROLL, xoffset, yoffset);
    }

    @Override
    public void textInputEventHandler(int codepoint)
    {
        trigger(OnEvent.Types.TEXT_INPUT, codepoint);
    }

    @Override
    public void windowCloseEventHandler()
    {
        trigger(OnEvent.Types.WINDOW_CLOSE);
    }

    @Override
    public void windowFocusEventHandler()
    {
        trigger(OnEvent.Types.WINDOW_FOCUS);
    }

    @Override
    public void windowLooseFocusEventHandler()
    {
        trigger(OnEvent.Types.WINDOW_LOOSE_FOCUS);
    }

    @Override
    public void windowIconifyEventHandler()
    {
        trigger(OnEvent.Types.WINDOW_ICONIFY);
    }

    @Override
    public void windowUniconifyEventHandler()
    {
        trigger(OnEvent.Types.WINDOW_UNICONIFY);
    }

    @Override
    public void windowMaximizeEventHandler()
    {
        trigger(OnEvent.Types.WINDOW_MAXIMIZE);
    }

    @Override
    public void windowUnmaximizeEventHandler()
    {
        trigger(OnEvent.Types.WINDOW_UNMAXIMIZE);
    }

    @Override
    public void windowPosEventHandler(int xpos, int ypos)
    {
        trigger(OnEvent.Types.WINDOW_POS, xpos, ypos);
    }

    @Override
    public void windowResizedEventHandler(int width, int height)
    {
        trigger(OnEvent.Types.WINDOW_RESIZED, width, height);
    }

    @Override
    public void joystickConnectedEventHandler(int jid, int event)
    {
        trigger(OnEvent.Types.JOYSTICK_CONNECTED, jid, event);
    }

    @Override
    public void joystickDisconnectedEventHandler(int jid, int event)
    {
        trigger(OnEvent.Types.JOYSTICK_DISCONNECTED, jid, event);
    }
}
