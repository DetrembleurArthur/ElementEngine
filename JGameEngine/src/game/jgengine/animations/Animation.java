package game.jgengine.animations;

import game.jgengine.binding.FloatProperty;
import game.jgengine.binding.Property;
import game.jgengine.graphics.gui.widgets.SmartShape;
import game.jgengine.tweening.TimedTweenAction;
import game.jgengine.tweening.TweenFunction;
import org.joml.Vector2f;
import org.joml.Vector4f;

import java.util.ArrayList;

public class Animation implements Runnable
{
	private SmartShape<?> gelem;
	private ArrayList<TimedTweenAction> actions;

	public Animation(SmartShape<?> gelem)
	{
		actions = new ArrayList<>();
		this.gelem = gelem;
	}

	public ArrayList<TimedTweenAction> getActions()
	{
		return actions;
	}

	public SmartShape<?> getGelem()
	{
		return gelem;
	}

	public void setGelem(SmartShape<?> gelem)
	{
		this.gelem = gelem;
	}

	private Animation setFromToAction(FloatProperty property, float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		actions.add(
			new TimedTweenAction(
				from,
				to,
				func, property::set,
				delay,
				cycles,
				back));
		return this;
	}

	private Animation setVector2fFromToAction(Property<Vector2f> property, Vector2f from, Vector2f to, TweenFunction func, float delay, int cycles, boolean back)
	{
		actions.add(
			new TimedTweenAction(
				from.x,
				to.x,
				func, value -> property.set(new Vector2f(value, property.getValue().y)),
				delay,
				cycles,
				back));
		actions.add(
			new TimedTweenAction(
				from.y,
				to.y,
				func, value -> property.set(new Vector2f(property.getValue().x, value)),
				delay,
				cycles,
				back));
		return this;
	}

	private Animation setVector4fFromToAction(Property<Vector4f> property, Vector4f from, Vector4f to, TweenFunction func, float delay, int cycles, boolean back)
	{
		actions.add(
			new TimedTweenAction(
				from.x,
				to.x,
				func, value -> property.set(new Vector4f(value, property.getValue().y, property.getValue().z, property.getValue().w)),
				delay,
				cycles,
				back));
		actions.add(
			new TimedTweenAction(
				from.y,
				to.y,
				func, value -> property.set(new Vector4f(property.getValue().x, value, property.getValue().z, property.getValue().w)),
				delay,
				cycles,
				back));
		actions.add(
			new TimedTweenAction(
				from.z,
				to.z,
				func, value -> property.set(new Vector4f(property.getValue().x, property.getValue().y, value, property.getValue().w)),
				delay,
				cycles,
				back));
		actions.add(
			new TimedTweenAction(
				from.w,
				to.w,
				func, value -> property.set(new Vector4f(property.getValue().x, property.getValue().y, property.getValue().z, value)),
				delay,
				cycles,
				back));
		return this;
	}

	private Animation setVector4fToAction(Property<Vector4f> property, Vector4f to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setVector4fFromToAction(property, property.getValue(), to, func, delay, cycles, back);
	}

	private Animation setVector2fToAction(Property<Vector2f> property, Vector2f to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setVector2fFromToAction(property, property.getValue(), to, func, delay, cycles, back);
	}

	private Animation setToAction(FloatProperty property, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setFromToAction(property, property.getValue(), to, func, delay, cycles, back);
	}

	public Animation fromToX(float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setFromToAction(gelem.xProperty, from, to, func, delay, cycles, back);
	}

	public Animation toX(float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setToAction(gelem.xProperty, to, func, delay, cycles, back);
	}

	public Animation fromToY(float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setFromToAction(gelem.yProperty, from, to, func, delay, cycles, back);
	}

	public Animation toY(float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setToAction(gelem.yProperty, to, func, delay, cycles, back);
	}

	public Animation fromToPosition(Vector2f from, Vector2f to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setVector2fFromToAction(gelem.positionProperty, from, to, func, delay, cycles, back);
	}

	public Animation toPosition(Vector2f to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setVector2fToAction(gelem.positionProperty, to, func, delay, cycles, back);
	}

	public Animation fromToWidth(float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setFromToAction(gelem.widthProperty, from, to, func, delay, cycles, back);
	}

	public Animation toWidth(float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setToAction(gelem.widthProperty, to, func, delay, cycles, back);
	}

	public Animation fromToHeight(float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setFromToAction(gelem.heightProperty, from, to, func, delay, cycles, back);
	}

	public Animation toHeight(float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setToAction(gelem.heightProperty, to, func, delay, cycles, back);
	}

	public Animation fromToSize(Vector2f from, Vector2f to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setVector2fFromToAction(gelem.sizeProperty, from, to, func, delay, cycles, back);
	}

	public Animation toSize(Vector2f to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setVector2fToAction(gelem.sizeProperty, to, func, delay, cycles, back);
	}

	public Animation fromToScaleX(float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setFromToAction(gelem.scaleXProperty, from, to, func, delay, cycles, back);
	}

	public Animation toScaleX(float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setToAction(gelem.scaleXProperty, to, func, delay, cycles, back);
	}

	public Animation fromToScaleY(float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setFromToAction(gelem.scaleYProperty, from, to, func, delay, cycles, back);
	}

	public Animation toScaleY(float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setToAction(gelem.scaleYProperty, to, func, delay, cycles, back);
	}

	public Animation fromToScale(Vector2f from, Vector2f to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setVector2fFromToAction(gelem.scaleProperty, from, to, func, delay, cycles, back);
	}

	public Animation toScale(Vector2f to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setVector2fToAction(gelem.scaleProperty, to, func, delay, cycles, back);
	}

	public Animation fromToRotation(float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setFromToAction(gelem.rotationProperty, from, to, func, delay, cycles, back);
	}

	public Animation toRotation(float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setToAction(gelem.rotationProperty, to, func, delay, cycles, back);
	}

	public Animation fromToR(float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setFromToAction(gelem.redProperty, from, to, func, delay, cycles, back);
	}

	public Animation toR(float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setToAction(gelem.redProperty, to, func, delay, cycles, back);
	}

	public Animation fromToGreen(float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setFromToAction(gelem.greenProperty, from, to, func, delay, cycles, back);
	}

	public Animation toGreen(float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setToAction(gelem.greenProperty, to, func, delay, cycles, back);
	}

	public Animation fromToBlue(float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setFromToAction(gelem.blueProperty, from, to, func, delay, cycles, back);
	}

	public Animation toBlue(float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setToAction(gelem.blueProperty, to, func, delay, cycles, back);
	}

	public Animation fromToOpacity(float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setFromToAction(gelem.opacityProperty, from, to, func, delay, cycles, back);
	}

	public Animation toOpacity(float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setToAction(gelem.opacityProperty, to, func, delay, cycles, back);
	}

	public Animation fromToColor(Vector4f from, Vector4f to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setVector4fFromToAction(gelem.fillColorProperty, from, to, func, delay, cycles, back);
	}

	public Animation toColor(Vector4f to, TweenFunction func, float delay, int cycles, boolean back)
	{
		return setVector4fToAction(gelem.fillColorProperty, to, func, delay, cycles, back);
	}

	public void start()
	{
		for(var all : actions)
		{
			all.start();
		}
	}

	public void restart()
	{
		for(var all : actions)
		{
			all.restart();
		}
	}

	public void stop()
	{
		for(var all : actions)
		{
			all.stop();
		}
	}

	public boolean isFinished()
	{
		for(var all : actions)
		{
			if(!all.isFinished())
				return false;
		}
		return true;
	}

	@Override
	public void run()
	{
		for(var all : actions)
		{
			all.run();
		}
	}
}
