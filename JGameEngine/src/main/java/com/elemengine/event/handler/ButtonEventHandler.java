package com.elemengine.event.handler;

public interface ButtonEventHandler
{
	void buttonPressedEventHandler(int button);
	void buttonReleasedEventHandler(int button);
	void buttonRepeatedEventHandler(int button);
}
