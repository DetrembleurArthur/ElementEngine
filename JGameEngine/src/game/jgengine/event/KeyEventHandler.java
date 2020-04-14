package game.jgengine.event;

public interface KeyEventHandler
{
	void keyPressedEventHandler(int key);

	void keyReleasedEventHandler(int key);

	void keyRepeatedEventHandler(int key);
}
