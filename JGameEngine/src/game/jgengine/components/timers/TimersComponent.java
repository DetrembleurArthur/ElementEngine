package game.jgengine.components.timers;

import com.mysql.cj.log.Log;
import game.jgengine.components.Component;
import game.jgengine.debug.Logs;
import game.jgengine.entity.GameObject;
import game.jgengine.time.DynamicTimer;
import game.jgengine.utils.LaterList;

import java.util.ArrayList;

public class TimersComponent extends Component implements Runnable
{
	private LaterList<DynamicTimer> timers;

	public TimersComponent(GameObject gameObject)
	{
		super(gameObject);
		timers = new LaterList<>();
	}

	public void add(DynamicTimer timer)
	{
		timers.addLater(timer);
		timer.activate();
	}



	@Override
	public void run()
	{
		for(var timer : timers)
		{
			timer.run();
			if(timer.isFinished() && timer.isActionRunned())
				timers.removeLater(timer);
		}
		timers.sync();
	}
}
