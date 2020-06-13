package game.jgengine.time;

import game.jgengine.debug.Logs;
import org.jetbrains.annotations.NotNull;

public class SyncTimer extends DynamicTimer
{
	public SyncTimer(float maxDelay, int maxPeriod, @NotNull Runnable action)
	{
		super(maxDelay, maxPeriod, action);
	}

	@Override
	public void run()
	{
		if(isRunning())
			test();
	}
}
