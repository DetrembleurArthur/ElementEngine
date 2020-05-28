package game.jgengine.graphics.camera;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera2D extends Camera
{
	private Vector2f position;
	private OrthoProjectionSettings orthoProjSettings;

	public Camera2D()
	{
		this(new Vector2f());
	}

	public Camera2D(Vector2f position)
	{
		super();
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
}
