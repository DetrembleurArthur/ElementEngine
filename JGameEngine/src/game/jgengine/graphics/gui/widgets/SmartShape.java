package game.jgengine.graphics.gui.widgets;

import game.jgengine.animations.Animation;
import game.jgengine.binding.FloatProperty;
import game.jgengine.binding.Property;
import game.jgengine.debug.Logs;
import game.jgengine.entity.Dynamic;
import game.jgengine.graphics.gui.event.*;
import game.jgengine.graphics.rendering.Renderer;
import game.jgengine.graphics.shapes.Shape;
import game.jgengine.scripting.Script;
import game.jgengine.tweening.*;
import org.joml.Vector2f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.HashMap;

public class SmartShape<T extends Shape> extends EventManager implements Dynamic
{
	public interface Configure<T extends Shape>
	{
		void configure(T shape);
	}

	private final T shape;
	private ArrayList<Script> scripts;
	private HashMap<String, Animation> animations;
	private ArrayList<Animation> runningAnimations;

	public final Property<Vector2f> positionProperty = new Property<>()
	{
		@Override
		public void setValue(Vector2f value)
		{
			xProperty.set(value.x);
			yProperty.set(value.y);
		}

		@Override
		public Vector2f getValue()
		{
			return shape.getPosition2D();
		}
	};

	public final FloatProperty xProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			shape.setX(value);
		}

		@Override
		public Float getValue()
		{
			return shape.getX();
		}
	};

	public final FloatProperty yProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			shape.setY(value);
		}

		@Override
		public Float getValue()
		{
			return shape.getY();
		}
	};

	public final Property<Vector2f> sizeProperty = new Property<>()
	{
		@Override
		public void setValue(Vector2f value)
		{
			widthProperty.set(value.x);
			heightProperty.set(value.y);
		}

		@Override
		public Vector2f getValue()
		{
			return shape.getSize2D();
		}
	};

	public final FloatProperty widthProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			shape.setWidth(value);
		}

		@Override
		public Float getValue()
		{
			return shape.getWidth();
		}
	};

	public final FloatProperty heightProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			shape.setHeight(value);
		}

		@Override
		public Float getValue()
		{
			return shape.getHeight();
		}
	};

	public final FloatProperty rotationProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			shape.setRotation(value);
		}

		@Override
		public Float getValue()
		{
			return shape.getRotation2D();
		}
	};

	public final Property<Vector4f> fillColorProperty = new Property<>()
	{
		@Override
		public void setValue(Vector4f value)
		{
			redProperty.set(value.x);
			greenProperty.set(value.y);
			blueProperty.set(value.z);
			opacityProperty.set(value.w);
		}

		@Override
		public Vector4f getValue()
		{
			return shape.getFillColor();
		}
	};

	public final FloatProperty redProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			shape.setR(value);
		}

		@Override
		public Float getValue()
		{
			return shape.getR();
		}
	};

	public final FloatProperty greenProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			shape.setG(value);
		}

		@Override
		public Float getValue()
		{
			return shape.getG();
		}
	};

	public final FloatProperty blueProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			shape.setB(value);
		}

		@Override
		public Float getValue()
		{
			return shape.getB();
		}
	};

	public final FloatProperty opacityProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			shape.setOpacity(value);
		}

		@Override
		public Float getValue()
		{
			return shape.getOpacity();
		}
	};

	public final FloatProperty scaleXProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			shape.setScaleX(value);
		}

		@Override
		public Float getValue()
		{
			return shape.getScaleX();
		}
	};

	public final FloatProperty scaleYProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			shape.setScaleY(value);
		}

		@Override
		public Float getValue()
		{
			return shape.getScaleY();
		}
	};

	public final Property<Vector2f> scaleProperty = new Property<>()
	{
		@Override
		public void setValue(Vector2f value)
		{
			scaleXProperty.set(value.x);
			scaleYProperty.set(value.y);
		}

		@Override
		public Vector2f getValue()
		{
			return shape.getScale2D();
		}
	};

	protected SmartShape(T shape)
	{
		this.shape = shape;
		this.scripts = new ArrayList<>();
		this.animations = new HashMap<>();
		this.runningAnimations = new ArrayList<>();
	}

	public void addScript(Script script)
	{
		scripts.add(script);
	}

	public Animation newAnimation(String id)
	{
		var animation = new Animation(this);
		animations.put(id, animation);
		return animation;
	}

	public Animation getAnimation(String id)
	{
		return animations.get(id);
	}

	public void startAnimation(String id)
	{
		var animation = animations.get(id);
		animation.restart();
		if(!runningAnimations.contains(animation))
			runningAnimations.add(animation);
	}

	public void stopAnimation(String id)
	{
		animations.get(id).stop();
		runningAnimations.remove(animations.get(id));
	}

	private void runAnimations()
	{
		for(int i = 0; i < runningAnimations.size(); i++)
		{
			Animation animation = runningAnimations.get(i);
			if(animation.isFinished())
			{
				runningAnimations.remove(animation);
				i--;
			}
			else
				animation.run();
		}
	}

	private void runScripts()
	{
		for(Script script : scripts)
		{
			script.run();
		}
	}

	@Override
	public void run()
	{
		pollEvent();
		runScripts();
		runAnimations();
	}

	public T getShape()
	{
		return shape;
	}

	public void with(Configure<T> configure)
	{
		configure.configure(shape);
	}

	public void onMouseHovering(ActionEvent action)
	{
		if(!onEvent(MouseHoveringEvent.class, action))
		{
			addEvent(new MouseHoveringEvent(this).addActionEvent(action));
		}
	}

	public void onMouseEntered(ActionEvent action)
	{
		if(!onEvent(MouseEnteredEvent.class, action))
		{
			addEvent(new MouseEnteredEvent(this).addActionEvent(action));
		}
	}

	public void onMouseExited(ActionEvent action)
	{
		if(!onEvent(MouseExitedEvent.class, action))
		{
			addEvent(new MouseExitedEvent(this).addActionEvent(action));
		}
	}

	public void onMouseMoved(ActionEvent action)
	{
		if(!onEvent(MouseMoveEvent.class, action))
		{
			addEvent(new MouseMoveEvent(this).addActionEvent(action));
		}
	}

	public void onMouseButtonClicked(ActionEvent action, boolean repeated)
	{
		if(!onEvent(MouseButtonClickEvent.class, action))
		{
			addEvent(new MouseButtonClickEvent(this, repeated).addActionEvent(action));
		}
	}

	public void onMouseLeftButtonClicked(ActionEvent action, boolean repeated)
	{
		if(!onEvent(MouseLeftButtonClickEvent.class, action))
		{
			addEvent(new MouseLeftButtonClickEvent(this, repeated).addActionEvent(action));
		}
	}

	public void onMouseRightButtonClicked(ActionEvent action, boolean repeated)
	{
		if(!onEvent(MouseRightButtonClickEvent.class, action))
		{
			addEvent(new MouseRightButtonClickEvent(this, repeated).addActionEvent(action));
		}
	}

	public void onMouseMiddleButtonClicked(ActionEvent action, boolean repeated)
	{
		if(!onEvent(MouseMiddleButtonClickEvent.class, action))
		{
			addEvent(new MouseMiddleButtonClickEvent(this, repeated).addActionEvent(action));
		}
	}

	public void onMouseButtonReleased(ActionEvent action)
	{
		if(!onEvent(MouseButtonReleasedEvent.class, action))
		{
			addEvent(new MouseButtonReleasedEvent(this).addActionEvent(action));
		}
	}

	public void onMouseLeftButtonReleased(ActionEvent action)
	{
		if(!onEvent(MouseLeftButtonRealeasedEvent.class, action))
		{
			addEvent(new MouseLeftButtonRealeasedEvent(this).addActionEvent(action));
		}
	}

	public void onMouseRightButtonReleased(ActionEvent action)
	{
		if(!onEvent(MouseRightButtonRealeasedEvent.class, action))
		{
			addEvent(new MouseRightButtonRealeasedEvent(this).addActionEvent(action));
		}
	}

	public void onMouseMiddleButtonReleased(ActionEvent action)
	{
		if(!onEvent(MouseMiddleButtonRealeasedEvent.class, action))
		{
			addEvent(new MouseMiddleButtonRealeasedEvent(this).addActionEvent(action));
		}
	}

	public void onMouseButtonDraged(ActionEvent action)
	{
		if(!onEvent(MouseButtonDragEvent.class, action))
		{
			addEvent(new MouseButtonDragEvent(this).addActionEvent(action));
		}
	}

	private void enableMouseDragging(int orientation)
	{
		MouseButtonDragEvent event = getEvent(MouseButtonDragEvent.class);
		if(event != null)
		{
			event.setDragActionEvent(orientation);
		}
		else
		{
			event = new MouseButtonDragEvent(this);
			event.setDragActionEvent(orientation);
			addEvent(event);
		}
	}

	public void enableMouseDragging()
	{
		enableMouseDragging(MouseButtonDragEvent.Orientation.BOTH);
	}

	public void enableHorizontalMouseDragging()
	{
		enableMouseDragging(MouseButtonDragEvent.Orientation.HORIZONTAL);
	}

	public void enableVerticalMouseDragging()
	{
		enableMouseDragging(MouseButtonDragEvent.Orientation.VERTICAL);
	}

	public void changeDraggingOrientation(int orientation)
	{
		clearEvent(MouseButtonDragEvent.class);
		enableMouseDragging(orientation);
	}

	public void onPositionChanged(ActionEvent action)
	{
		if(!onEvent(PositionChangedEvent.class, action))
		{
			addEvent(new PositionChangedEvent(this).addActionEvent(action));
		}
	}

	public void onMouseButtonDoubleClicked(ActionEvent action)
	{
		if(!onEvent(MouseButtonDoubleClickEvent.class, action))
		{
			addEvent(new MouseButtonDoubleClickEvent(this).addActionEvent(action));
		}
	}

	public void onFillColorChanged(ActionEvent action)
	{
		if(!onEvent(FillColorChangedEvent.class, action))
		{
			addEvent(new FillColorChangedEvent(this).addActionEvent(action));
		}
	}

	@Override
	public void destroy()
	{
		shape.destroy();
	}

	@Override
	public void draw()
	{
		shape.draw();
	}

	@Override
	public void draw(Renderer renderer)
	{
		shape.draw(renderer);
	}
}
