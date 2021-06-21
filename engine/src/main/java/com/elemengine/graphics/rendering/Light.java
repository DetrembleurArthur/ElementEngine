package com.elemengine.graphics.rendering;

import org.joml.*;

public class Light
{
	private Vector2f position;
	private Vector4f color;
	private float radius;
	private Vector4f basicPower;
	private float lightFade = 1f;

	public Light(Vector2f position, Vector4f color, float radius)
	{
		this.position = position;
		this.color = color;
		this.radius = radius;
		this.basicPower = new Vector4f();
	}

	public Vector2f getPosition()
	{
		return position;
	}

	public void setPosition(Vector2f position)
	{
		this.position = position;
	}

	public Vector4f getColor()
	{
		return color;
	}

	public void setColor(Vector4f color)
	{
		this.color = color;
	}

	public float getRadius()
	{
		return radius;
	}

	public void setRadius(float radius)
	{
		this.radius = radius;
	}

	public Vector4f getBasicPower()
	{
		return basicPower;
	}

	public void setBasicPower(Vector4f basicPower)
	{
		this.basicPower = basicPower;
	}

	public float getLightFade()
	{
		return lightFade;
	}

	public void setLightFade(float lightFade)
	{
		this.lightFade = lightFade;
	}
}
