package game.jgengine.entity;

import game.jgengine.components.Component;
import game.jgengine.components.forces.MoveManagerComponent;
import game.jgengine.components.scripts.ScriptsComponent;
import game.jgengine.components.animations.AnimationsComponent;
import game.jgengine.components.event.EventManagerComponent;
import game.jgengine.components.properties.CommonPropertiesComponent;
import game.jgengine.components.properties.TextPropertyComponent;
import game.jgengine.components.properties.ValuePropertyComponent;
import game.jgengine.graphics.vertex.Mesh;
import game.jgengine.graphics.rendering.Texture;
import game.jgengine.sys.Game;
import game.jgengine.utils.MathUtil;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class GameObject extends GraphicElement
{
	private ArrayList<Component> components;

	public GameObject(Mesh mesh, Texture texture)
	{
		super(mesh, texture);
	}

	public <T extends Component> boolean addComponent(T component)
	{
		if(components == null) components = new ArrayList<>();
		for(Component c : components)
		{
			if(c.getClass().equals(component.getClass()))
				return false;
		}
		components.add(component);
		return true;
	}

	@SuppressWarnings("unchecked")
	public <T extends Component> T getComponent(Class<T> cclass)
	{
		if(components == null)
		{
			components = new ArrayList<>();
			return null;
		}
		for(Component c : components)
		{
			if(c.getClass().equals(cclass))
				return (T)c;
		}
		return null;
	}

	public <T extends Component> boolean removeComponent(Class<T> cclass)
	{
		return components.removeIf(component -> component.getClass().equals(cclass));
	}

	public <T extends Component> T accessComponent(Class<T> cclass)
	{
		T component = getComponent(cclass);
		if(component == null)
		{
			try
			{
				addComponent(component = cclass.getConstructor(GameObject.class).newInstance(this));
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
			{
				e.printStackTrace();
			}
		}
		return component;
	}

	public EventManagerComponent events()
	{
		return accessComponent(EventManagerComponent.class);
	}

	public CommonPropertiesComponent properties()
	{
		return accessComponent(CommonPropertiesComponent.class);
	}

	public TextPropertyComponent textProperty()
	{
		return accessComponent(TextPropertyComponent.class);
	}

	public ValuePropertyComponent valueProperty()
	{
		return accessComponent(ValuePropertyComponent.class);
	}

	public AnimationsComponent animations()
	{
		return accessComponent(AnimationsComponent.class);
	}

	public ScriptsComponent scripts()
	{
		return accessComponent(ScriptsComponent.class);
	}

	public MoveManagerComponent moves()
	{
		return accessComponent(MoveManagerComponent.class);
	}

	@Override
	public void run()
	{
		if(components != null)
		{
			for(Component component : components)
			{
				if(component instanceof Runnable)
					((Runnable) component).run();
			}
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

	public Vector2f getVectorComponent(@NotNull Vector2f pos)
	{
		if(pos.x == position.x && pos.y == position.y) return new Vector2f();
		return pos.sub(getPosition2D()).normalize();
	}

	public Vector2f getVectorComponent(float x, float y)
	{
		return getVectorComponent(new Vector2f(x, y));
	}

	public Vector2f getVectorComponent(Vector2f pos, Vector2f speed)
	{
		return getVectorComponent(pos).mul(speed);
	}

	public Vector2f getVectorComponent(float x, float y, float sx, float sy)
	{
		return getVectorComponent(new Vector2f(x, y), new Vector2f(sx, sy));
	}

	public Vector2f getVectorComponent(float x, float y, float s)
	{
		return getVectorComponent(x, y, s, s);
	}

	public Vector2f getVectorComponent(Vector2f pos, float s)
	{
		return getVectorComponent(pos, new Vector2f(s, s));
	}

	public void moveToward(Vector2f pos, Vector2f speed)
	{
		move(getVectorComponent(pos, speed));
	}

	public void moveToward(float x, float y,  float sx, float sy)
	{
		move(getVectorComponent(x, y, sx, sy));
	}

	public void moveToward(Vector2f pos, float speed)
	{
		move(getVectorComponent(pos, speed));
	}

	public void moveToward(float x, float y, float speed)
	{
		move(getVectorComponent(x, y, speed));
	}

	public void moveTowarddt(Vector2f pos, Vector2f speed)
	{
		movedt(getVectorComponent(pos, speed));
	}

	public void moveTowarddt(float x, float y,  float sx, float sy)
	{
		movedt(getVectorComponent(x, y, sx, sy));
	}

	public void moveTowarddt(Vector2f pos, float speed)
	{
		movedt(getVectorComponent(pos, speed));
	}

	public void moveTowarddt(float x, float y, float speed)
	{
		movedt(getVectorComponent(x, y, speed));
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
