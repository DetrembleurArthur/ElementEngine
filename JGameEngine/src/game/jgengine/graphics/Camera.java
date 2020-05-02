package game.jgengine.graphics;


import game.jgengine.event.Input;
import game.jgengine.graphics.vertex.GraphicElement;
import org.joml.*;

import java.lang.Math;

public class Camera
{
	private Matrix4f projectionMatrix;
	private Matrix4f viewMatrix;
	private Vector3f position;
	private Vector3f rotation;

	private float fov = 70f;
	private float aspect = 0f;
	private float znear = 0.001f;
	private float zfar = 1000f;

	private Vector2f oldMouse = new Vector2f();
	private float moveSpeed = 0.05f;
	private float mouseSensitivity = 0.15f;

	private float verticalAngle, horizontalAngle;

	public Camera(Vector3f position, Vector2f wsize)
	{
		this.position = position;
		this.projectionMatrix = new Matrix4f();
		this.viewMatrix = new Matrix4f();
		this.rotation = new Vector3f();
		setAspect(wsize.x / wsize.y);
		adjustProjection();
	}

	public void adjustProjection()
	{
		projectionMatrix.identity();
		projectionMatrix.perspective((float)Math.toRadians(fov), aspect, znear, zfar);
	}

	public float getZnear()
	{
		return znear;
	}

	public void setZnear(float znear)
	{
		this.znear = znear;
	}

	public float getZfar()
	{
		return zfar;
	}

	public void setZfar(float zfar)
	{
		this.zfar = zfar;
	}

	public float getAspect()
	{
		return aspect;
	}

	public void setAspect(float aspect)
	{
		this.aspect = aspect;
	}

	public float getFov()
	{
		return fov;
	}

	public void setFov(float fovd)
	{
		fov = fovd;
	}

	public Matrix4f getViewMatrix2D()
	{

		return viewMatrix;
	}

	public Matrix4f getViewMatrix()
	{
		viewMatrix.identity();
		viewMatrix.rotateX((float)Math.toRadians(rotation.x));
		viewMatrix.rotateY((float)Math.toRadians(rotation.y));
		viewMatrix.rotateZ((float)Math.toRadians(rotation.z));
		Vector3f negative = new Vector3f(-position.x, -position.y, -position.z);
		viewMatrix.translate(negative);

		return viewMatrix;
	}

	public Matrix4f getProjectionMatrix()
	{
		return projectionMatrix;
	}

	public Vector3f getPosition()
	{
		return position;
	}

	public Vector3f getDirection()
	{
		return new Vector3f(rotation.x / 1f,  rotation.y / 1f, rotation.z / 1f);
	}


	public void update(Vector2f mousePosition)
	{
		float mouseX = mousePosition.x;
		float mouseY = mousePosition.y;
		float dx = mouseX - oldMouse.x;
		float dy = mouseY - oldMouse.y;

		rotation = rotation.add(dy * mouseSensitivity,
				dx * mouseSensitivity, 0);


		oldMouse = new Vector2f(mouseX, mouseY);
	}

	public void update(Vector2f mousePosition, Transformable gelem)
	{
		float mouseX = mousePosition.x;
		float mouseY = mousePosition.y;
		float dx = mouseX - oldMouse.x;
		float dy = mouseY - oldMouse.y;

		verticalAngle -= dy * mouseSensitivity;
		horizontalAngle += dx * mouseSensitivity;

		float h = (float) (2.f * Math.cos(Math.toRadians(verticalAngle)));
		float v = (float) (2.f * Math.sin(Math.toRadians(verticalAngle)));

		float xo = (float) (h * Math.sin(Math.toRadians(-horizontalAngle)));
		float zo = (float) (h * Math.cos(Math.toRadians(-horizontalAngle)));

		position.set(gelem.getPosition().x + xo, gelem.getPosition().y - v, gelem.getPosition().z + zo);

		rotation.set(verticalAngle, -horizontalAngle, 0f);

		oldMouse = new Vector2f(mouseX, mouseY);
	}

	public Vector3f getForward()
	{
		float cosY = (float) Math.cos(Math.toRadians(rotation.y + 90));
		float sinY = (float) Math.sin(Math.toRadians(rotation.y + 90));
		float cosP = (float) Math.cos(Math.toRadians(rotation.x));
		float sinP = (float) Math.sin(Math.toRadians(rotation.x));

		return new Vector3f(cosY * cosP, sinP, sinY * cosP);
	}

	public Vector3f getRight()
	{
		float cosY = (float) Math.cos(Math.toRadians(rotation.y));
		float sinY = (float) Math.sin(Math.toRadians(rotation.y));

		return new Vector3f(cosY, 0, sinY);
	}

	public void left()
	{
		position.add(getRight().mul(-moveSpeed));
	}

	public void right()
	{
		position.add(getRight().mul(moveSpeed));
	}

	public void toward()
	{
		position.add(getForward().mul(-moveSpeed));
	}

	public void backward()
	{
		position.add(getForward().mul(moveSpeed));
	}

	public void upaxis()
	{
		position.add(new Vector3f(0, moveSpeed, 0));
	}

	public void downaxis()
	{
		position.add(new Vector3f(0, -moveSpeed, 0));
	}

	public void towardaxis()
	{
		position.add(new Vector3f(0, 0, -moveSpeed));
	}

	public void backwardaxis()
	{
		position.add(new Vector3f(0, 0, moveSpeed));
	}

	public void rightaxis()
	{
		position.add(new Vector3f(moveSpeed, 0, 0));
	}

	public void leftaxis()
	{
		position.add(new Vector3f(-moveSpeed, 0, 0));
	}

	public void setOldMouse(Vector2f old)
	{
		oldMouse = old;
	}

}
