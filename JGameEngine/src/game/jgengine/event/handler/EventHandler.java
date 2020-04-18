package game.jgengine.event.handler;

public interface EventHandler extends ButtonEventHandler, CursorEnterEventHandler, CursorPosEventHandler, DropEventHandler, KeyEventHandler, ScrollEventHandler, TextInputEventHandler, WindowCloseEventHandler, WindowFocusEventHandler, WindowIconifyEventHandler, WindowMaximizeEventHandler, WindowPosEventHandler, WindowResizeEventHandler
{
	@Override
	default void buttonPressedEventHandler(int button){}
	@Override
	default void buttonReleasedEventHandler(int button){}
	@Override
	default void buttonRepeatedEventHandler(int button){}
	@Override
	default void cursorEnteredEventHandler(){}
	@Override
	default void cursorExitedEventHandler(){}
	@Override
	default void cursorMovedEventHandler(double xpos, double ypos){}
	@Override
	default void dropEventHandler(String[] items){}
	@Override
	default void keyPressedEventHandler(int key){}
	@Override
	default void keyReleasedEventHandler(int key){}
	@Override
	default void keyRepeatedEventHandler(int key){}
	@Override
	default void scrollEventHandler(double xoffset, double yoffset){}
	@Override
	default void textInputEventHandler(int codepoint){}
	@Override
	default void windowCloseEventHandler(){}
	@Override
	default void windowFocusEventHandler(){}
	@Override
	default void windowLooseFocusEventHandler(){}
	@Override
	default void windowIconifyEventHandler(){}
	@Override
	default void windowUniconifyEventHandler(){}
	@Override
	default void windowMaximizeEventHandler(){}
	@Override
	default void windowUnmaximizeEventHandler(){}
	@Override
	default void windowPosEventHandler(int xpos, int ypos){}
	@Override
	default void windowResizedEventHandler(int width, int height){}
}
