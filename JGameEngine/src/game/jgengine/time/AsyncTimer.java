package game.jgengine.time;

import game.jgengine.debug.Logs;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AsyncTimer extends DynamicTimer
{
	private Thread thread;

	public AsyncTimer(float maxDelay, int maxPeriod, @NotNull Runnable action)
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
