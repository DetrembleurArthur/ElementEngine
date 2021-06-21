package com.elemengine.graphics.camera;

import org.joml.Matrix4f;

public abstract class Camera
{
	protected Matrix4f projectionMatrix;
	protected Matrix4f viewMatrix;
	protected Matrix4f invProjectionMatrix;
	protected Matrix4f invViewMatrix;

	protected Camera()
	{
		projectionMatrix = new Matrix4f();
		viewMatrix = new Matrix4f();
		invProjectionMatrix = new Matrix4f();
		invViewMatrix = new Matrix4f();
	}

	public abstract Matrix4f updateProjectionMatrix();
	public abstract Matrix4f updateViewMatrix();

	public Matrix4f getProjectionMatrix()
	{
		return projectionMatrix;
	}

	public Matrix4f getViewMatrix()
	{
		return viewMatrix;
	}

	public Matrix4f getInvProjectionMatrix()
	{
		projectionMatrix.invert(invProjectionMatrix);
		return invProjectionMatrix;
	}

	public Matrix4f getInvViewMatrix()
	{
		viewMatrix.invert(invViewMatrix);
		return invViewMatrix;
	}
}
