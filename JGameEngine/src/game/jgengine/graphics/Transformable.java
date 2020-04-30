package game.jgengine.graphics;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transformable
{
	private Matrix4f trMatrix;
	private Vector3f origin;
	private Vector3f position;
	private float rotation;
	private Vector3f scale;

	public Transformable()
	{
		trMatrix.identity();
	}

	public Matrix4f getMatrix()
	{
		return trMatrix;
	}

	public Vector3f getOrigin()
	{
		return origin;
	}

	public void setOrigin(Vector3f origin)
	{
		this.origin = origin;
	}

	public Vector3f getPosition()
	{
		return position;
	}

	public void setPosition(Vector3f position)
	{
		this.position = position;
	}

	public float getRotation()
	{
		return rotation;
	}

	public void setRotation(float rotation)
	{
		this.rotation = rotation;
	}

	public Vector3f getScale()
	{
		return scale;
	}

	public void setScale(Vector3f scale)
	{
		this.scale = scale;
	}

	public void update()
	{

	}
}
