package game.jgengine.graphics.camera;

import org.joml.Matrix4f;

public abstract class Camera
{
	protected Matrix4f projectionMatrix;
	protected Matrix4f viewMatrix;

	protected Camera()
	{
		projectionMatrix = new Matrix4f();
		viewMatrix = new Matrix4f();
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
}
