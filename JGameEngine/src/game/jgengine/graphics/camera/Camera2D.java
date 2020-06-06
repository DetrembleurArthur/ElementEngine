package game.jgengine.graphics.camera;

import game.jgengine.event.Input;
import game.jgengine.sys.Window;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;

public class Camera2D extends Camera
{
	private Vector2f position;
	private OrthoProjectionSettings orthoProjSettings;
	private Vector2f speed;

	public static final KeySet SPECTATOR_KEY_SET = new KeySet()
			.set("right", GLFW_KEY_RIGHT)
			.set("left", GLFW_KEY_LEFT)
			.set("up", GLFW_KEY_UP)
			.set("down", GLFW_KEY_DOWN);

	public Camera2D()
	{
		this(new Vector2f());
	}

	public Camera2D(Vector2f position)
	{
		super();
		this.speed = new Vector2f(15, 15);
		this.position = position;
		orthoProjSettings = new OrthoProjectionSettings();
		updateProjectionMatrix();
	}

	public Camera2D(OrthoProjectionSettings settings)
	{
		this(new Vector2f(), settings);
	}

	public Camera2D(Vector2f position, OrthoProjectionSettings settings)
	{
		super();
		this.speed = new Vector2f(15, 15);
		this.position = position;
		this.orthoProjSettings = settings;
		updateProjectionMatrix();
	}

	@Override
	public Matrix4f updateProjectionMatrix()
	{
		return projectionMatrix.identity().ortho(
				orthoProjSettings.getLeft(),
				orthoProjSettings.getRight(),
				orthoProjSettings.getBottom(),
				orthoProjSettings.getUp(),
				0f,
				100f);
	}

	@Override
	public Matrix4f updateViewMatrix()
	{
		return viewMatrix.identity().lookAt(
				new Vector3f(position.x, position.y, 20f),
				new Vector3f(0f, 0f, -1f).add(position.x, position.y, 0f),
				new Vector3f(0f, 1f, 0f));
	}

	public Vector2f getPosition()
	{
		return position;
	}

	public void setPosition(Vector2f position)
	{
		this.position = position;
	}

	public OrthoProjectionSettings getOrthoProjSettings()
	{
		return orthoProjSettings;
	}

	public void setOrthoProjSettings(OrthoProjectionSettings orthoProjSettings)
	{
		this.orthoProjSettings = orthoProjSettings;
	}

	public void move(float x, float y)
	{
		this.position.x += x;
		this.position.y += y;
	}

	public void activateKeys(Window window, KeySet keys)
	{
		if(Input.isKeyPressed(window, keys.get("right")))
		{
			move(speed.x, 0);
		}
		if(Input.isKeyPressed(window, keys.get("left")))
		{
			move(-speed.x, 0);
		}
		if(Input.isKeyPressed(window, keys.get("up")))
		{
			move(0, -speed.y);
		}
		if(Input.isKeyPressed(window, keys.get("down")))
		{
			move(0, speed.y);
		}
	}
}
