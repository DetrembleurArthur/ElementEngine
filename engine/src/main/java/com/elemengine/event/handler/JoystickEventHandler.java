package com.elemengine.event.handler;

public interface JoystickEventHandler
{
	void joystickConnectedEventHandler(int jid, int event);
	void joystickDisconnectedEventHandler(int jid, int event);
}
