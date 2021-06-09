package com.elemengine.graphics.camera;


import com.elemengine.event.Input;
import com.elemengine.entity.Transformable;
import com.elemengine.sys.Window;
import org.joml.*;

import java.lang.Math;

import static org.lwjgl.glfw.GLFW.*;

public class Camera3D extends Camera
{
	private Vector3f position;
	private Vector3f rotation;

	private PerspProjectionSettings projectionSettings;
	private Vector2f oldMouse = new Vector2f();
	private float moveSpeed = 0.15f;
	private float mouseSensitivity = 0.15f;

	private float verticalAngle, horizontalAngle;
	
	public static final KeySet SPECTATOR_KEY_SET = new KeySet()
			.set("left", GLFW_KEY_LEFT)
			.set("right", GLFW_KEY_RIGHT)
			.set("toward", GLFW_KEY_UP)
			.set("backward", GLFW_KEY_DOWN)
			.set("up", GLFW_KEY_SPACE)
			.set("down", GLFW_KEY_RIGHT_SHIFT);

	public Camera3D()
	{
		this(new Vector3f(0f, 0f, 1f));
	}

	public Camera3D(Vector3f position)
	{
		super();
		this.position = position;
		this.rotation = new Vector3f();
		projectionSettings = new PerspProjectionSettings();
		updateProjectionMatrix();
	}

	public Camera3D(PerspProjectionSettings settings)
	{
		this(new Vector3f(0f, 0f, 1f), settings);
	}

	public Camera3D(Vector3f position, PerspProjectionSettings settings)
	{
		super();
		this.position = position;
		this.rotation = new Vector3f();
		this.projectionSettings = settings;
		updateProjectionMatrix();
	}

	@Override
	public Matrix4f updateProjectionMatrix()
	{
		return projectionMatrix.identity().perspective(
				projectionSettings.getFovAsRadians(),
				projectionSettings.getAspect(),
				projectionSettings.getZnear(),
				projectionSettings.getZfar());
	}

	@Override
	public Matrix4f updateViewMatrix()
	{
		return viewMatrix.identity()
				.rotateX((float)Math.toRadians(rotation.x))
				.rotateY((float)Math.toRadians(rotation.y))
				.rotateZ((float)Math.toRadians(rotation.z))
				.translate(new Vector3f(-position.x, -position.y, -position.z));
	}

	public Vector3f getPosition()
	{
		return position;
	}


	public PerspProjectionSettings getProjectionSettings()
	{
		return projectionSettings;
	}

	public void setProjectionSettings(PerspProjectionSettings projectionSettings)
	{
		this.projectionSettings = projectionSettings;
	}

	public void update(Vector2f mousePosition)
	{
		float mouseX = mousePosition.x;
		float mouseY = mousePosition.y;
		float dx = mouseX - oldMouse.x;
		float dy = mouseY - oldMouse.y;

		rotation = rotation.add(dy * mouseSensitivity, dx * mouseSensitivity, 0);

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


	public void activateKeys(KeySet keySet, Window window)
	{
		if(Input.isKeyPressed(window, keySet.get("left")))
		{
			if(Input.isRightButtonPressed(window))
				leftaxis();
			else
				left();
		}

		if(Input.isKeyPressed(window, keySet.get("right")))
		{
			if(Input.isRightButtonPressed(window))
				rightaxis();
			else
				right();
		}

		if(Input.isKeyPressed(window, keySet.get("toward")))
		{
			if(Input.isRightButtonPressed(window))
				towardaxis();
			else
				toward();
		}

		if(Input.isKeyPressed(window, keySet.get("backward")))
		{
			if(Input.isRightButtonPressed(window))
				backwardaxis();
			else
				backward();
		}

		if(Input.isKeyPressed(window, keySet.get("down")))
		{
			downaxis();
		}

		if(Input.isKeyPressed(window, keySet.get("up")))
		{
			upaxis();
		}
	}

	public float getMoveSpeed()
	{
		return moveSpeed;
	}

	public void setMoveSpeed(float moveSpeed)
	{
		this.moveSpeed = moveSpeed;
	}

	public float getMouseSensitivity()
	{
		return mouseSensitivity;
	}

	public void setMouseSensitivity(float mouseSensitivity)
	{
		this.mouseSensitivity = mouseSensitivity;
	}
}
