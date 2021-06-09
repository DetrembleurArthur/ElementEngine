package com.elemengine.time;


public class AsyncTimer extends DynamicTimer
{
	private Thread thread;

	public AsyncTimer(float maxDelay, int maxPeriod, Runnable action)
	{
		super(maxDelay, maxPeriod, action);
		thread = new Thread(this);
	}

	public Thread getThread()
	{
		return thread;
	}

	public void start()
	{
		if(!thread.isAlive())
			thread.start();
	}

	@Override
	public void run()
	{
		activate();
		while(isRunning())
		{
			test();
		}
	}
}
