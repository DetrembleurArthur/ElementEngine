package com.elemengine.event.handler.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OnEvent
{
    enum Types
    {
        BUTTON_PRESSED,
        BUTTON_RELEASED,
        BUTTON_REPEATED,
        CURSOR_ENTERED,
        CURSOR_EXITED,
        CURSOR_MOVED,
        DROP,
        KEY_PRESSED,
        KEY_RELEASED,
        KEY_REPEATED,
        SCROLL,
        TEXT_INPUT,
        WINDOW_CLOSE,
        WINDOW_FOCUS,
        WINDOW_LOOSE_FOCUS,
        WINDOW_ICONIFY,
        WINDOW_UNICONIFY,
        WINDOW_MAXIMIZE,
        WINDOW_UNMAXIMIZE,
        WINDOW_POS,
        WINDOW_RESIZED,
        JOYSTICK_CONNECTED,
        JOYSTICK_DISCONNECTED
    }

    Types value();
}

