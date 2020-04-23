package game.jgengine.graphics;


import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera2D
{
	private Matrix4f projectionMatrix;
	private Matrix4f viewMatrix;
	private Vector2f position;

	public Camera2D(Vector2f position, Vector2f wsize)
	{
		this.position = position;
		this.projectionMatrix = new Matrix4f();
		this.viewMatrix = new Matrix4f();
		adjustProjection(wsize);
	}

	public void adjustProjection(Vector2f wsize)
	{
		projectionMatrix.identity();
		projectionMatrix.ortho(0.0f, wsize.x, wsize.y, 0f, 0f, 100.0f);
	}

	public Matrix4f getViewMatrix()
	{
		Vector3f cameraFront = new Vector3f(0f, 0f, -1f);
		Vector3f cameraUp = new Vector3f(0f, 1f, 0f);
		viewMatrix.identity();
		viewMatrix.lookAt(new Vector3f(position.x, position.y, 20.f),
				cameraFront.add(position.x, position.y, 0f),
				cameraUp);
		return viewMatrix;
	}

	public Matrix4f getProjectionMatrix()
	{
		return projectionMatrix;
	}

	public Vector2f getPosition()
	{
		return position;
	}
}
