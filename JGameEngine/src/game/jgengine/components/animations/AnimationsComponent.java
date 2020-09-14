package game.jgengine.components.animations;

import com.mysql.cj.log.Log;
import game.jgengine.components.Component;
import game.jgengine.debug.Logs;
import game.jgengine.entity.GameObject;

import java.util.ArrayList;
import java.util.HashMap;

public class AnimationsComponent extends Component implements Runnable
{
	private HashMap<String, Animation> animations;
	private ArrayList<Animation> runningAnimations;

	public AnimationsComponent(GameObject relativeObject)
	{
		super(relativeObject);
		animations = new HashMap<>();
		runningAnimations = new ArrayList<>();
	}

	public Animation create(String id)
	{
		var animation = new Animation(getRelativeObject());
		animations.put(id, animation);
		return animation;
	}

	public boolean isFinished(String id)
	{
		return animations.get(id).isFinished();
	}

	public boolean isSleep(String id)
	{
		return animations.get(id).isSleep();
	}

	public Animation getAnimation(String id)
	{
		return animations.get(id);
	}

	public void startAnimation(String id)
	{
		var animation = animations.get(id);
		animation.restart();
		if(!runningAnimations.contains(animation))
			runningAnimations.add(animation);
	}

	public void stopAnimation(String id)
	{
		animations.get(id).stop();
		runningAnimations.remove(animations.get(id));
	}

	@Override
	public void run()
	{
		for(int i = 0; i < runningAnimations.size(); i++)
		{
			Animation animation = runningAnimations.get(i);
			if(animation.isFinished())
			{
				runningAnimations.remove(animation);
				i--;
			}
			else
				animation.run();
		}
	}
}
