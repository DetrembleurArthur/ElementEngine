package game.jgengine.graphics;


import game.jgengine.event.Input;
import game.jgengine.graphics.vertex.GraphicElement;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera
{
	private Matrix4f projectionMatrix;
	private Matrix4f viewMatrix;
	private Vector3f position;
	private Vector3f rotation;

	public Vector2f center = new Vector2f();
	private Vector2f oldMouse = new Vector2f();
	private float moveSpeed = 0.005f;
	private float mouseSensitivity = 0.0015f;

	private float verticalAngle, horizontalAngle;

	public Camera(Vector3f position, Vector2f wsize)
	{
		this.position = position;
		this.projectionMatrix = new Matrix4f();
		this.viewMatrix = new Matrix4f();
		this.rotation = new Vector3f();
		this.center = new Vector2f(wsize.x / 2, wsize.y / 2);
		adjustProjection(70f, wsize.x / wsize.y, 0.1f, 1000.f);
	}

	public void adjustProjection2D(Vector2f wsize)
	{
		projectionMatrix.identity();
		projectionMatrix.ortho(0.0f, wsize.x, wsize.y, 0f, 0f, 100.0f);
	}

	public void adjustProjection(float fov, float aspect, float near, float far)
	{
		projectionMatrix.identity();

		float tanFOV = (float) Math.tan(Math.toRadians(fov / 2));
		float range = far - near;

		projectionMatrix.set(1, 1, 1.0f / (aspect * tanFOV));
		projectionMatrix.set(1, 1, 1.0f / tanFOV);
		projectionMatrix.set(2, 2, -((far + near) / range));
		projectionMatrix.set(2, 3, -1.0f);
		projectionMatrix.set(3, 2, -((2 * far * near) / range));
		projectionMatrix.set(3, 3, 0.0f);
	}

	public Matrix4f getViewMatrix2D()
	{
		Vector3f cameraFront = new Vector3f(0f, 0f, -1f);
		Vector3f cameraUp = new Vector3f(0f, 1f, 0f);
		viewMatrix.identity();
		viewMatrix.lookAt(new Vector3f(position.x, position.y, 20.f),
				cameraFront.add(position.x, position.y, 0f),
				cameraUp);
		return viewMatrix;
	}

	public Matrix4f getViewMatrix()
	{
		Matrix4f result = new Matrix4f().identity();

		Vector3f negative = new Vector3f(-position.x, -position.y, -position.z);
		Matrix4f translationMatrix = new Matrix4f().translate(negative);
		Matrix4f rotXMatrix = new Matrix4f().rotate(rotation.x, new Vector3f(1, 0, 0));
		Matrix4f rotYMatrix = new Matrix4f().rotate(rotation.y, new Vector3f(0, 1, 0));
		Matrix4f rotZMatrix = new Matrix4f().rotate(rotation.z, new Vector3f(0, 0, 1));

		Matrix4f rotationMatrix = rotZMatrix.mul(rotYMatrix.mul(rotXMatrix));

		result = translationMatrix.mul(rotationMatrix);

		return result;
	}

	public Matrix4f getProjectionMatrix()
	{
		return projectionMatrix;
	}

	public Vector3f getPosition()
	{
		return position;
	}


	public void update(Vector2f mousePosition)
	{
		float mouseX = mousePosition.x;
		float mouseY = mousePosition.y;
		float dx = mouseX - center.x;
		float dy = mouseY - center.y;

		rotation = rotation.add(-dy * mouseSensitivity,
				-dx * mouseSensitivity, 0);

		oldMouse = new Vector2f(mouseX, mouseY);
	}

	public void update(Vector2f mousePosition, GraphicElement gelem)
	{
		float mouseX = mousePosition.x;
		float mouseY = mousePosition.y;
		float dx = mouseX - center.x;
		float dy = mouseY - center.y;

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

	public void left()
	{
		float x = (float)Math.sin(Math.toRadians(rotation.y)) * moveSpeed;
		float z = (float)Math.cos(Math.toRadians(rotation.y)) * moveSpeed;
		position = position.add(-z, 0, x);
	}

	public void right()
	{
		float x = (float)Math.sin(Math.toRadians(rotation.y)) * moveSpeed;
		float z = (float)Math.cos(Math.toRadians(rotation.y)) * moveSpeed;
		position = position.add(z, 0, x);
	}

	public void in()
	{
		float x = (float)Math.sin(Math.toRadians(rotation.y)) * moveSpeed;
		float z = (float)Math.cos(Math.toRadians(rotation.y)) * moveSpeed;
		position = position.add(-x, 0, -z);
	}

	public void out()
	{
		float x = (float)Math.sin(Math.toRadians(rotation.y)) * moveSpeed;
		float z = (float)Math.cos(Math.toRadians(rotation.y)) * moveSpeed;
		position = position.add(x, 0, z);
	}

	public void up()
	{
		position = position.add(0, moveSpeed, 0);
	}

	public void down()
	{
		position = position.add(0, -moveSpeed, 0);
	}


}
