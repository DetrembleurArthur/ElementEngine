package com.elemengine.graphics.camera;

import com.elemengine.sys.Window;

public class OrthoProjectionSettings
{
	private float left;
	private float right;
	private float bottom;
	private float up;

	public OrthoProjectionSettings()
	{
		this(0f, 0f, 0f, 0f);
	}

	public OrthoProjectionSettings(Window window)
	{
		this(0f, window.getSize().x, window.getSize().y, 0f);
	}

	public OrthoProjectionSettings(float left, float right, float bottom, float up)
	{
		this.left = left;
		this.right = right;
		this.bottom = bottom;
		this.up = up;
	}

	public void update(Window window)
	{
		left = 0f;
		right = window.getSize().x;
		bottom = window.getSize().y;
		up = 0f;
	}

	public float getLeft()
	{
		return left;
	}

	public void setLeft(float left)
	{
		this.left = left;
	}

	public float getRight()
	{
		return right;
	}

	public void setRight(float right)
	{
		this.right = right;
	}

	public float getBottom()
	{
		return bottom;
	}

	public void setBottom(float bottom)
	{
		this.bottom = bottom;
	}

	public float getUp()
	{
		return up;
	}

	public void setUp(float up)
	{
		this.up = up;
	}
}
