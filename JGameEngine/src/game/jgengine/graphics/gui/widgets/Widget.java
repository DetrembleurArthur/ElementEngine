package game.jgengine.graphics.gui.widgets;

import game.jgengine.graphics.gui.event.*;
import game.jgengine.graphics.shapes.Shape;
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

	protected Widget(T shape)
	{
		this.shape = shape;
		this.animationsSequences = new ArrayList<>();
		lastAnimationsSequence = null;
		lastActionPack = null;
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
						shape.getX(),
						position.x,
						func, shape::setX,
						delay,
						maxCycle,
						back))
			.addAction(
				new TimedTweenAction(
						shape.getY(),
						position.y,
						func, shape::setY,
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

	public Widget<T> toSize(Vector2f size, TweenFunction func ,float delay, int maxCycle, boolean back)
	{
		packInitialize(pack ->
			pack
			.addAction(
				new TimedTweenAction(
						shape.getSize().x,
						size.x,
						func, value -> shape.setSize(value, shape.getSize().y),
						delay,
						maxCycle,
						back))
			.addAction(
				new TimedTweenAction(
						shape.getSize().y,
						size.y,
						func, value -> shape.setSize(shape.getSize().x, value),
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

	public Widget<T> toRotation(float angleDegree, TweenFunction func ,float delay, int maxCycle, boolean back)
	{
		packInitialize(pack ->
			pack
				.addAction(
					new TimedTweenAction(
							shape.getRotation2D(),
							angleDegree,
							func, shape::setRotation,
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
							shape.getR(),
							color.x,
							func, shape::setR,
							delay,
							maxCycle,
							back))
				.addAction(
						new TimedTweenAction(
								shape.getG(),
								color.y,
								func, shape::setG,
								delay,
								maxCycle,
								back))
				.addAction(
						new TimedTweenAction(
								shape.getB(),
								color.z,
								func, shape::setB,
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
						shape.getR(),
						r,
						func, shape::setR,
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
										shape.getG(),
										g,
										func, shape::setG,
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
										shape.getB(),
										b,
										func, shape::setB,
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
							shape.getOpacity(),
							opacity,
							func, shape::setOpacity,
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

	@Override
	public void run()
	{
		super.run();
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
			addEvent(new MouseHoveringEvent(getShape()).addActionEvent(action));
		}
	}

	public void onMouseEntered(ActionEvent action)
	{
		if(!onEvent(MouseEnteredEvent.class, action))
		{
			addEvent(new MouseEnteredEvent(getShape()).addActionEvent(action));
		}
	}

	public void onMouseExited(ActionEvent action)
	{
		if(!onEvent(MouseExitedEvent.class, action))
		{
			addEvent(new MouseExitedEvent(getShape()).addActionEvent(action));
		}
	}

	public void onMouseMoved(ActionEvent action)
	{
		if(!onEvent(MouseMoveEvent.class, action))
		{
			addEvent(new MouseMoveEvent(getShape()).addActionEvent(action));
		}
	}

	public void onMouseButtonClicked(ActionEvent action, boolean repeated)
	{
		if(!onEvent(MouseButtonClickEvent.class, action))
		{
			addEvent(new MouseButtonClickEvent(getShape(), repeated).addActionEvent(action));
		}
	}

	public void onMouseLeftButtonClicked(ActionEvent action, boolean repeated)
	{
		if(!onEvent(MouseLeftButtonClickEvent.class, action))
		{
			addEvent(new MouseLeftButtonClickEvent(getShape(), repeated).addActionEvent(action));
		}
	}

	public void onMouseRightButtonClicked(ActionEvent action, boolean repeated)
	{
		if(!onEvent(MouseRightButtonClickEvent.class, action))
		{
			addEvent(new MouseRightButtonClickEvent(getShape(), repeated).addActionEvent(action));
		}
	}

	public void onMouseMiddleButtonClicked(ActionEvent action, boolean repeated)
	{
		if(!onEvent(MouseMiddleButtonClickEvent.class, action))
		{
			addEvent(new MouseMiddleButtonClickEvent(getShape(), repeated).addActionEvent(action));
		}
	}

	public void onMouseButtonReleased(ActionEvent action)
	{
		if(!onEvent(MouseButtonReleasedEvent.class, action))
		{
			addEvent(new MouseButtonReleasedEvent(getShape()).addActionEvent(action));
		}
	}

	public void onMouseLeftButtonReleased(ActionEvent action)
	{
		if(!onEvent(MouseLeftButtonRealeasedEvent.class, action))
		{
			addEvent(new MouseLeftButtonRealeasedEvent(getShape()).addActionEvent(action));
		}
	}

	public void onMouseRightButtonReleased(ActionEvent action)
	{
		if(!onEvent(MouseRightButtonRealeasedEvent.class, action))
		{
			addEvent(new MouseRightButtonRealeasedEvent(getShape()).addActionEvent(action));
		}
	}

	public void onMouseMiddleButtonReleased(ActionEvent action)
	{
		if(!onEvent(MouseMiddleButtonRealeasedEvent.class, action))
		{
			addEvent(new MouseMiddleButtonRealeasedEvent(getShape()).addActionEvent(action));
		}
	}

	public void onMouseButtonDraged(ActionEvent action)
	{
		if(!onEvent(MouseButtonDragEvent.class, action))
		{
			addEvent(new MouseButtonDragEvent(getShape()).addActionEvent(action));
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
			event = new MouseButtonDragEvent(getShape());
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
			addEvent(new PositionChangedEvent(getShape()).addActionEvent(action));
		}
	}

	public void onMouseButtonDoubleClicked(ActionEvent action)
	{
		if(!onEvent(MouseButtonDoubleClickEvent.class, action))
		{
			addEvent(new MouseButtonDoubleClickEvent(getShape()).addActionEvent(action));
		}
	}

	public void onFillColorChanged(ActionEvent action)
	{
		if(!onEvent(FillColorChangedEvent.class, action))
		{
			addEvent(new FillColorChangedEvent(getShape()).addActionEvent(action));
		}
	}
}
