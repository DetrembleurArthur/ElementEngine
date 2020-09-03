package game.jgengine.entity;

import game.jgengine.utils.FreeReflectable;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Transformable extends FreeReflectable
{
	private Matrix4f trMatrix;
	protected Vector3f position;
	protected Vector3f rotation;
	protected Vector3f scale;
	protected Vector3f size;
	protected Vector3f origin;

	public Transformable(Vector3f position, Vector3f rotation, Vector3f size)
	{
		trMatrix = new Matrix4f();
		trMatrix.identity();
		this.position = position;
		this.rotation = rotation;
		this.scale = new Vector3f(1, 1, 1);
		this.size = size;
		this.origin = new Vector3f(0);
	}

	public Matrix4f getMatrix()
	{
		return trMatrix;
	}

	public float getX()
	{
		return position.x;
	}

	public void setX(float x)
	{
		position.x = x;
	}

	public float getY()
	{
		return position.y;
	}

	public void setY(float y)
	{
		position.y = y;
	}

	public Vector3f getPosition()
	{
		return position;
	}

	public void setPosition(Vector3f position)
	{
		this.position = position;
	}

	public void setPosition(float x, float y)
	{
		this.position.x = x;
		this.position.y = y;
		this.position.z = 0;
	}

	public void setPosition(Vector2f position)
	{
		this.position = new Vector3f(position.x, position.y, 0);
	}

	public Vector2f getPosition2D()
	{
		return new Vector2f(position.x, position.y);
	}

	public Vector3f getRotation()
	{
		return rotation;
	}

	public float getRotation2D()
	{
		return rotation.z;
	}

	public void setRotation(Vector3f rotation)
	{
		this.rotation = rotation;
	}

	public void setRotation(float angleDegre)
	{
		this.rotation = new Vector3f(0, 0, angleDegre);
	}

	public Vector3f getScale()
	{
		return scale;
	}

	public Vector2f getScale2D()
	{
		return new Vector2f(scale.x, scale.y);
	}

	public void setScale(Vector3f scale)
	{
		this.scale = new Vector3f(scale);
	}

	public void setScale(Vector2f scale2d)
	{
		this.scale = new Vector3f(scale2d.x, scale2d.y, scale.z);
	}

	public Vector3f getSize()
	{
		return size;
	}

	public Vector2f getSize2D()
	{
		return new Vector2f(size.x, size.y);
	}

	public void setSize(Vector3f size)
	{
		Vector3f old = this.size;
		this.size = new Vector3f(size);
		this.origin.mul(size.div(old));
	}

	public void setSize(Vector2f size)
	{
		setSize(new Vector3f(size.x, size.y, this.size.z));
	}

	public void setSize(float w, float h)
	{
		setSize(new Vector3f(w, h, this.size.z));
	}

	public void setWidth(float w)
	{
		setSize(w, this.size.y);
	}

	public void setHeight(float h)
	{
		setSize(this.size.x, h);
	}

	public float getWidth()
	{
		return this.size.x;
	}

	public float getHeight()
	{
		return this.size.y;
	}

	public Vector3f getOrigin()
	{
		return origin;
	}

	public Vector2f getOrigin2D()
	{
		return new Vector2f(origin.x, origin.y);
	}

	public void setOrigin(Vector3f origin)
	{
		this.origin = new Vector3f(origin);
	}

	public void setOrigin(Vector2f origin)
	{
		this.origin = new Vector3f(origin.x, origin.y, 0);
	}

	public void setOrigin(float x, float y)
	{
		this.origin.x = x;
		this.origin.y = y;
		this.origin.z = 0;
	}

	public void setTopLeftOrigin()
	{
		setOrigin(0, 0);
	}

	public void setTopRightOrigin()
	{
		setOrigin(size.x, 0);
	}

	public void setBottomLeftOrigin()
	{
		setOrigin(0, size.y);
	}

	public void setBottomRightOrigin()
	{
		setOrigin(size.x, size.y);
	}

	public void setCenterOrigin()
	{
		setOrigin(size.x / 2, size.y / 2);
	}


	public Vector2f getTopLeftPosition()
	{
		return new Vector2f(getPosition2D()).sub(getOrigin2D());
	}

	public void setTopLeftPosition(Vector2f pos)
	{
		setPosition(new Vector2f(pos).add(getOrigin2D()));
	}

	public Vector2f getCenter()
	{
		return getTopLeftPosition().add(getSize2D().div(2));
	}


	public Matrix4f getTransformMatrix()
	{
		Vector3f scaledOrigin = new Vector3f(origin).mul(scale);
		return trMatrix.identity()
				.translate(new Vector3f(position).sub(scaledOrigin))
				.translate(scaledOrigin)
				.rotateX((float)Math.toRadians(rotation.x))
				.rotateY((float)Math.toRadians(rotation.y))
				.rotateZ((float)Math.toRadians(rotation.z))
				.translate(new Vector3f(origin).mul(scale).negate())
				.scale(size).scale(scale);
	}
}
