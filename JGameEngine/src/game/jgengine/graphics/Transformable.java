package game.jgengine.graphics;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transformable
{
	private Matrix4f trMatrix;
	private Vector3f position;
	private Vector3f rotation;
	private Vector3f scale;

	public Transformable(Vector3f position, Vector3f rotation, Vector3f scale)
	{
		trMatrix = new Matrix4f();
		trMatrix.identity();
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}

	public Matrix4f getMatrix()
	{
		return trMatrix;
	}

	public Vector3f getPosition()
	{
		return position;
	}

	public void setPosition(Vector3f position)
	{
		this.position = position;
	}

	public Vector3f getRotation()
	{
		return rotation;
	}

	public void setRotation(Vector3f rotation)
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

	public Matrix4f getTransformMatrix()
	{
		trMatrix.identity();
		trMatrix.translate(position);
		Matrix4f rotXMatrix = new Matrix4f().rotateX(rotation.x);
		Matrix4f rotYMatrix = new Matrix4f().rotateY(rotation.y);
		Matrix4f rotZMatrix = new Matrix4f().rotateZ(rotation.z);
		Matrix4f scaleMatrix = new Matrix4f().scale(scale);
		trMatrix.mul(rotXMatrix.mul(rotYMatrix.mul(rotZMatrix)).mul(scaleMatrix));
		return trMatrix;
	}
}
