package com.elemengine.components.moves;

import com.elemengine.components.Component;
import com.elemengine.entity.GameObject;
import com.elemengine.sys.Application;
import com.elemengine.utils.MathUtil;
import org.joml.Vector2f;

public class MoveManagerComponent extends Component implements Runnable
{
	private Vector2f speed;
	private Vector2f acceleration;
	private boolean speedZeroCondition = false;
	private float rotationSpeed;
	private float rotationAcceleration;
	private boolean rotationSpeedZeroCondition = false;

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

	public float getRotationSpeed()
	{
		return rotationSpeed;
	}

	public void setRotationSpeed(float rotationSpeed)
	{
		this.rotationSpeed = rotationSpeed;
	}

	public float getRotationAcceleration()
	{
		return rotationAcceleration;
	}

	public void setRotationAcceleration(float rotationAcceleration)
	{
		this.rotationAcceleration = rotationAcceleration;
	}

	public boolean isRotationSpeedZeroCondition()
	{
		return rotationSpeedZeroCondition;
	}

	public void setRotationSpeedZeroCondition(boolean rotationSpeedZeroCondition)
	{
		this.rotationSpeedZeroCondition = rotationSpeedZeroCondition;
	}

	private void updateSpeed()
	{
		speed.add(new Vector2f(acceleration).mul((float) Application.DT));
		if(speedZeroCondition)
		{
			if(MathUtil.sameSign(acceleration.x, speed.x)) speed.x = 0;
			if(MathUtil.sameSign(acceleration.y, speed.y)) speed.y = 0;
		}
		rotationSpeed += rotationAcceleration * Application.DT;
		if(rotationSpeedZeroCondition)
		{
			if(MathUtil.sameSign(rotationSpeed, rotationAcceleration)) rotationSpeed = 0;
		}
	}

	public boolean isSpeedZeroCondition()
	{
		return speedZeroCondition;
	}

	public void setSpeedZeroCondition(boolean speedZeroCondition)
	{
		this.speedZeroCondition = speedZeroCondition;
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
		getRelativeObject().rotatedt(rotationSpeed);
	}
}
