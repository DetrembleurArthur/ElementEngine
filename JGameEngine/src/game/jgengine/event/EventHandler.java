package game.jgengine.event;

public interface EventHandler
{
	void keyPressedEventHandler(int key);
	void keyReleasedEventHandler(int key);
	void keyRepeatedEventHandler(int key);
	void buttonPressedEventHandler(int button);
	void buttonReleasedEventHandler(int button);
	void buttonRepeatedEventHandler(int button);
	void cursorMovedEventHandler(double x, double y);
	void scrollEventHandler(double xoffset, double yoffset);
	void cursorEnteredEventHandler();
	void cursorExitedEventHandler();
	void windowResizedEventHandler(int width, int height);
	void windowFocusedEventHandler();
	void windowLoosedFocusEventHandler();
	void windowCloseEventHandler();
	void windowPosEventHandler(int xpos, int ypos);
	void windowIconifyEventHandler();
	void windowUniconifyEventHandler();
	void windowMaximizeEventHandler();
	void windowUnmaximizeEventHandler();
	void textInputEventHandler(int codepoint);
}
