package game.jgengine.components.forces;

import game.jgengine.components.Component;
import game.jgengine.entity.GameObject;
import game.jgengine.sys.Game;
import game.jgengine.utils.MathUtil;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;

public class MoveManagerComponent extends Component implements Runnable
{
	private Vector2f speed;
	private Vector2f acceleration;
	private boolean zeroCondition = true;

	public MoveManagerComponent(GameObject relativeObject)
	{
		super(relativeObject);
		speed = new Vector2f();
		acceleration = new Vector2f();
	}

	public void setSpeed(Vector2f speed)
	{
		this.speed = speed;
	}

	public void setAcceleration(Vector2f acceleration)
	{
		this.acceleration = acceleration;
	}

	public Vector2f getSpeed()
	{
		return speed;
	}

	public Vector2f getAcceleration()
	{
		return acceleration;
	}

	private void updateSpeed()
	{
		speed.add(new Vector2f(acceleration).mul((float) Game.DT));
		if(zeroCondition)
		{
			if(MathUtil.sameSign(acceleration.x, speed.x)) speed.x = 0;
			if(MathUtil.sameSign(acceleration.y, speed.y)) speed.y = 0;
		}
	}

	public boolean isZeroCondition()
	{
		return zeroCondition;
	}

	public void setZeroCondition(boolean zeroCondition)
	{
		this.zeroCondition = zeroCondition;
	}

	public void speedToward(Vector2f position, Vector2f speed)
	{
		this.speed = getRelativeObject().getVectorComponent(position).mul(speed);
	}

	public void accelerationToward(Vector2f position, Vector2f acceleration)
	{
		this.acceleration = getRelativeObject().getVectorComponent(position).mul(acceleration);
	}

	@Override
	public void run()
	{
		updateSpeed();
		getRelativeObject().movedt(speed);
	}
}
