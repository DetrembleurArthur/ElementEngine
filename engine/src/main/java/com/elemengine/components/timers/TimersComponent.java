package com.elemengine.components.timers;

import com.elemengine.components.Component;
import com.elemengine.entity.GameObject;
import com.elemengine.time.DynamicTimer;
import com.elemengine.utils.LaterList;

public class TimersComponent extends Component implements Runnable
{
    private final LaterList<DynamicTimer> timers;

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
        for (var timer : timers)
        {
            timer.run();
            if (timer.isFinished() && timer.isActionRunned())
                timers.removeLater(timer);
        }
        timers.sync();
    }
}
