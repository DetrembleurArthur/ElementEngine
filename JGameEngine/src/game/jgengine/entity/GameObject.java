package game.jgengine.entity;

import game.jgengine.graphics.rendering.Renderer;
import game.jgengine.graphics.vertex.Mesh;
import game.jgengine.graphics.rendering.Texture;
import game.jgengine.sys.Game;
import game.jgengine.utils.Colors;
import game.jgengine.utils.MathUtil;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class GameObject extends GraphicElement
{
	private Texture texture = null;

	public GameObject(Mesh mesh, Texture texture)
	{
		super(mesh);
		setTexture(texture);
	}

	public Texture getTexture()
	{
		return texture;
	}

	public void setTexture(Texture texture)
	{
		this.texture = texture;
	}

	public void setTextureAndResize(Texture texture, float mulFactor)
	{
		setTexture(texture);
		setSize(new Vector2f(texture.getDimension()).mul(mulFactor));
	}

	public void setTextureAndResize(Texture texture)
	{
		setTextureAndResize(texture, 1f);
	}

	@Override
	public void draw()
	{
		if(texture != null)
		{
			texture.active();
			texture.bind();
			super.draw();
			texture.unbind();
		}
		else
		{
			super.draw();
		}
	}


	public void move(float vx, float vy, float vz)
	{
		position.x += vx;
		position.y += vy;
		position.z += vz;
	}

	public void move(float vx, float vy)
	{
		move(vx, vy, 0);
	}

	public void move(@NotNull Vector2f speed)
	{
		move(speed.x, speed.y);
	}

	public void move(@NotNull Vector3f speed)
	{
		move(speed.x, speed.y, speed.z);
	}

	public void movedt(float vx, float vy, float vz)
	{
		position.x += vx * Game.DT;
		position.y += vy * Game.DT;
		position.z += vz * Game.DT;
	}

	public void movedt(float vx, float vy)
	{
		movedt(vx, vy, 0);
	}

	public void movedt(@NotNull Vector2f speed)
	{
		movedt(speed.x, speed.y);
	}

	public void movedt(@NotNull Vector3f speed)
	{
		movedt(speed.x, speed.y, speed.z);
	}

	public Vector2f getComponent(@NotNull Vector2f pos)
	{
		if(pos.x == position.x && pos.y == position.y) return new Vector2f();
		return pos.sub(getPosition2D()).normalize();
	}

	public Vector2f getComponent(float x, float y)
	{
		return getComponent(new Vector2f(x, y));
	}

	public Vector2f getComponent(Vector2f pos, Vector2f speed)
	{
		return getComponent(pos).mul(speed);
	}

	public Vector2f getComponent(float x, float y, float sx, float sy)
	{
		return getComponent(new Vector2f(x, y), new Vector2f(sx, sy));
	}

	public Vector2f getComponent(float x, float y, float s)
	{
		return getComponent(x, y, s, s);
	}

	public Vector2f getComponent(Vector2f pos, float s)
	{
		return getComponent(pos, new Vector2f(s, s));
	}

	public void moveToward(Vector2f pos, Vector2f speed)
	{
		move(getComponent(pos, speed));
	}

	public void moveToward(float x, float y,  float sx, float sy)
	{
		move(getComponent(x, y, sx, sy));
	}

	public void moveToward(Vector2f pos, float speed)
	{
		move(getComponent(pos, speed));
	}

	public void moveToward(float x, float y, float speed)
	{
		move(getComponent(x, y, speed));
	}

	public void moveTowarddt(Vector2f pos, Vector2f speed)
	{
		movedt(getComponent(pos, speed));
	}

	public void moveTowarddt(float x, float y,  float sx, float sy)
	{
		movedt(getComponent(x, y, sx, sy));
	}

	public void moveTowarddt(Vector2f pos, float speed)
	{
		movedt(getComponent(pos, speed));
	}

	public void moveTowarddt(float x, float y, float speed)
	{
		movedt(getComponent(x, y, speed));
	}

	public void rotate(float angle)
	{
		rotation.z += angle;
	}

	public void rotate(Vector3f rotation)
	{
		this.rotation.add(rotation);
	}

	public void rotate(float rx, float ry, float rz)
	{
		rotate(new Vector3f(rx, ry, rz));
	}

	public void rotatedt(float angle)
	{
		rotation.z += angle * Game.DT;
	}

	public void rotatedt(Vector3f rotation)
	{
		this.rotation.add(rotation.mul((float) Game.DT));
	}

	public void rotatedt(float rx, float ry, float rz)
	{
		rotatedt(new Vector3f(rx, ry, rz));
	}

	public float getRotation(Vector2f target)
	{
		return (float) Math.toDegrees(Math.atan2(-(position.y - target.y), -(position.x - target.x)));
	}

	public void rotateAround(Vector2f target, float angle)
	{
		setPosition(MathUtil.rotateAround(getPosition2D(), target, angle));
	}

	public void rotateAround(float x, float y, float angle)
	{
		rotateAround(new Vector2f(x, y), angle);
	}

	public void rotateAround(Vector2f target, Vector2f angle)
	{
		setPosition(MathUtil.rotateAround(getPosition2D(), target, angle));
	}

	public void rotateAround(float x, float y, float ax, float ay)
	{
		rotateAround(new Vector2f(x, y), new Vector2f(ax, ay));
	}

	public void rotateArounddt(Vector2f target, float angle)
	{
		setPosition(MathUtil.rotateAround(getPosition2D(), target, (float) (angle * Game.DT)));
	}

	public void rotateArounddt(float x, float y, float angle)
	{
		rotateAround(new Vector2f(x, y), angle);
	}

	public void rotateArounddt(Vector2f target, Vector2f angle)
	{
		setPosition(MathUtil.rotateAround(getPosition2D(), target, angle.mul((float) Game.DT)));
	}

	public void rotateArounddt(float x, float y, float ax, float ay)
	{
		rotateArounddt(new Vector2f(x, y), new Vector2f(ax, ay));
	}
}
