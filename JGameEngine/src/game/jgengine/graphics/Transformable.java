package game.jgengine.graphics;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Transformable
{
	private Matrix4f trMatrix;
	private Vector3f position;
	private Vector3f origin;
	private Vector3f rotation;
	private Vector3f scale;

	public Transformable(Vector3f position, Vector3f rotation, Vector3f scale)
	{
		trMatrix = new Matrix4f();
		trMatrix.identity();
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.origin = new Vector3f(0, 0, 0);
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

	public Vector3f getOrigin()
	{
		return origin;
	}

	public void setOrigin(Vector3f origin)
	{
		this.origin = new Vector3f(-origin.x, -origin.y, -origin.z);
	}

	public void setTopLeftOrigin()
	{
		origin = new Vector3f(0,0,0);
	}

	public void setTopRightOrigin()
	{
		origin = new Vector3f(-scale.x, 0, 0);
	}

	public void setBottomLeftOrigin()
	{
		origin = new Vector3f(0, -scale.y, 0);
	}

	public void setBottomRightOrigin()
	{
		origin = new Vector3f(-scale.x, -scale.y, 0);
	}

	public void setCenterOrigin()
	{
		origin = new Vector3f(-scale.x / 2, -scale.y / 2, 0);
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
		return trMatrix.identity()
				.translate(position)
				.translate(origin)
				.rotateX((float)Math.toRadians(rotation.x))
				.rotateY((float)Math.toRadians(rotation.y))
				.rotateZ((float)Math.toRadians(rotation.z))
				.scale(scale);
	}
}
