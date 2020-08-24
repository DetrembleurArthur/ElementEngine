package game.jgengine.graphics.gui.widgets;

import game.jgengine.binding.FloatProperty;
import game.jgengine.binding.Property;
import game.jgengine.graphics.gui.event.*;
import game.jgengine.graphics.shapes.Shape;
import game.jgengine.scripting.Script;
import game.jgengine.tweening.*;
import org.joml.Vector2f;
import org.joml.Vector4f;

import java.util.ArrayList;

public class Widget<T extends Shape> extends EventManager
{
	public interface Configure<T extends Shape>
	{
		void configure(T shape);
	}

	private final T shape;
	private ArrayList<AnimationsSequence> animationsSequences;
	private AnimationsPack lastActionPack;
	private AnimationsSequence lastAnimationsSequence;
	private ArrayList<Script> scripts;

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
			return shape.getSize();
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

	protected Widget(T shape)
	{
		this.shape = shape;
		this.animationsSequences = new ArrayList<>();
		lastAnimationsSequence = null;
		lastActionPack = null;
		this.scripts = new ArrayList<>();
	}

	private interface PackInitializer
	{
		void init(AnimationsPack pack);
	}

	private void packInitialize(PackInitializer init)
	{
		AnimationsSequence animationsSequence = lastAnimationsSequence != null ? lastAnimationsSequence : new AnimationsSequence();
		AnimationsPack pack = lastActionPack != null ? lastActionPack : new AnimationsPack();
		init.init(pack);
		if(lastActionPack == null)
		{
			animationsSequence.addActionPack(pack);
			lastActionPack = pack;
		}
		lastAnimationsSequence = animationsSequence;
		animationsSequences.add(animationsSequence);
	}

	public Widget<T> toPosition(Vector2f position, TweenFunction func ,float delay, int maxCycle, boolean back)
{
	packInitialize(pack ->
			pack
					.addAction(
							new TimedTweenAction(
									positionProperty.getValue().x,
									position.x,
									func, value -> positionProperty.set(new Vector2f(value, positionProperty.getValue().y)),
									delay,
									maxCycle,
									back))
					.addAction(
							new TimedTweenAction(
									positionProperty.getValue().y,
									position.y,
									func, value -> positionProperty.set(new Vector2f(positionProperty.getValue().x, value)),
									delay,
									maxCycle,
									back)
					)
	);
	return this;
}

	public Widget<T> toPosition(Vector2f position, TweenFunction func ,float delay)
	{
		return toPosition(position, func, delay, 0, false);
	}

	public Widget<T> toX(float x, TweenFunction func ,float delay, int maxCycle, boolean back)
	{
		packInitialize(pack ->
			pack
				.addAction(
					new TimedTweenAction(
						xProperty.getValue(),
						x,
						func, xProperty::set,
						delay,
						maxCycle,
						back))
		);
		return this;
	}

	public Widget<T> toX(float x, TweenFunction func ,float delay)
	{
		return toX(x, func, delay, 0, false);
	}

	public Widget<T> toY(float y, TweenFunction func ,float delay, int maxCycle, boolean back)
	{
		packInitialize(pack ->
			pack
				.addAction(
					new TimedTweenAction(
						yProperty.getValue(),
						y,
						func, yProperty::set,
						delay,
						maxCycle,
						back))
		);
		return this;
	}

	public Widget<T> toY(float y, TweenFunction func ,float delay)
	{
		return toY(y, func, delay, 0, false);
	}

	public Widget<T> toSize(Vector2f size, TweenFunction func ,float delay, int maxCycle, boolean back)
	{
		packInitialize(pack ->
			pack
			.addAction(
				new TimedTweenAction(
						sizeProperty.getValue().x,
						size.x,
						func, value -> sizeProperty.set(new Vector2f(value, shape.getSize().y)),
						delay,
						maxCycle,
						back))
			.addAction(
				new TimedTweenAction(
						sizeProperty.getValue().y,
						size.y,
						func, value -> sizeProperty.set(new Vector2f(shape.getSize().x, value)),
						delay,
						maxCycle,
						back)
			)
		);
		return this;
	}

	public Widget<T> toSize(Vector2f size, TweenFunction func ,float delay)
	{
		return toSize(size, func, delay, 0, false);
	}

	public Widget<T> toWidth(float width, TweenFunction func ,float delay, int maxCycle, boolean back)
	{
		packInitialize(pack ->
			pack
				.addAction(
					new TimedTweenAction(
						widthProperty.getValue(),
						width,
						func, widthProperty::set,
						delay,
						maxCycle,
						back))
		);
		return this;
	}

	public Widget<T> toWidth(float width, TweenFunction func ,float delay)
	{
		return toWidth(width, func, delay, 0, false);
	}

	public Widget<T> toHeight(float height, TweenFunction func ,float delay, int maxCycle, boolean back)
	{
		packInitialize(pack ->
			pack
				.addAction(
					new TimedTweenAction(
						heightProperty.getValue(),
						height,
						func, heightProperty::set,
						delay,
						maxCycle,
						back))
		);
		return this;
	}

	public Widget<T> toHeight(float height, TweenFunction func ,float delay)
	{
		return toWidth(height, func, delay, 0, false);
	}

	public Widget<T> toRotation(float angleDegree, TweenFunction func ,float delay, int maxCycle, boolean back)
	{
		packInitialize(pack ->
			pack
				.addAction(
					new TimedTweenAction(
						rotationProperty.getValue(),
						angleDegree,
						func, rotationProperty::set,
						delay,
						maxCycle,
						back))
		);
		return this;
	}

	public Widget<T> toRotation(float angleDegree, TweenFunction func ,float delay)
	{
		return toRotation(angleDegree, func, delay, 0, false);
	}

	public Widget<T> toColor(Vector4f color, TweenFunction func , float delay, int maxCycle, boolean back)
	{
		packInitialize(pack ->
			pack
				.addAction(
					new TimedTweenAction(
							fillColorProperty.getValue().x,
							color.x,
							func, value -> fillColorProperty.set(new Vector4f(value, fillColorProperty.getValue().y, fillColorProperty.getValue().z, fillColorProperty.getValue().w)),
							delay,
							maxCycle,
							back))
				.addAction(
						new TimedTweenAction(
							fillColorProperty.getValue().y,
							color.y,
							func, value -> fillColorProperty.set(new Vector4f(fillColorProperty.getValue().x, value, fillColorProperty.getValue().z, fillColorProperty.getValue().w)),
							delay,
							maxCycle,
							back))
				.addAction(
						new TimedTweenAction(
							fillColorProperty.getValue().x,
							color.z,
							func, value -> fillColorProperty.set(new Vector4f(fillColorProperty.getValue().x, fillColorProperty.getValue().y, value, fillColorProperty.getValue().w)),
							delay,
							maxCycle,
							back))
		);
		return this;
	}

	public Widget<T> toColor(Vector4f color, TweenFunction func , float delay)
	{
		return toColor(color, func, delay, 0, false);
	}

	public Widget<T> toR(float r, TweenFunction func , float delay, int maxCycle, boolean back)
	{
		packInitialize(pack ->
			pack
				.addAction(
					new TimedTweenAction(
						redProperty.getValue(),
						r,
						func, redProperty::set,
						delay,
						maxCycle,
						back))
		);
		return this;
	}

	public Widget<T> toR(float r, TweenFunction func , float delay)
	{
		return toR(r, func, delay, 0, false);
	}

	public Widget<T> toG(float g, TweenFunction func , float delay, int maxCycle, boolean back)
	{
		packInitialize(pack ->
			pack
				.addAction(
					new TimedTweenAction(
						greenProperty.getValue(),
						g,
						func, greenProperty::set,
						delay,
						maxCycle,
						back))
		);
		return this;
	}

	public Widget<T> toG(float g, TweenFunction func , float delay)
	{
		return toG(g, func, delay, 0, false);
	}

	public Widget<T> toB(float b, TweenFunction func , float delay, int maxCycle, boolean back)
	{
		packInitialize(pack ->
			pack
				.addAction(
					new TimedTweenAction(
						blueProperty.getValue(),
						b,
						func, blueProperty::set,
						delay,
						maxCycle,
						back))
		);
		return this;
	}

	public Widget<T> toB(float b, TweenFunction func , float delay)
	{
		return toR(b, func, delay, 0, false);
	}

	public Widget<T> toOpacity(float opacity, TweenFunction func , float delay, int maxCycle, boolean back)
	{
		packInitialize(pack ->
			pack
				.addAction(
					new TimedTweenAction(
							opacityProperty.getValue(),
							opacity,
							func, opacityProperty::set,
							delay,
							maxCycle,
							back))
		);
		return this;
	}

	public Widget<T> toOpacity(float opacity, TweenFunction func , float delay)
	{
		return toOpacity(opacity, func, delay, 0, false);
	}

	public void startAnimations()
	{
		for(AnimationsSequence animationsSequence : animationsSequences)
		{
			animationsSequence.start();
		}
	}

	public void stopActionPack()
	{
		lastActionPack = null;
	}

	public void stopAnimationSequence()
	{
		stopActionPack();
		lastAnimationsSequence = null;
	}

	private void runAnimations()
	{
		ArrayList<AnimationsSequence> removeList = new ArrayList<>();
		for(AnimationsSequence animationsSequence : animationsSequences)
		{
			animationsSequence.run();
			if(animationsSequence.isFinished())
				removeList.add(animationsSequence);
		}
		for(AnimationsSequence animationsSequence : removeList)
		{
			animationsSequences.remove(animationsSequence);
		}
	}

	public void addScript(Script script)
	{
		scripts.add(script);
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
		super.run();
		runAnimations();
		runScripts();
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
}
