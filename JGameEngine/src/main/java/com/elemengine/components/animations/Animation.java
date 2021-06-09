package com.elemengine.components.animations;

import com.elemengine.components.properties.CommonPropertiesComponent;
import com.elemengine.entity.GameObject;
import com.elemengine.tweening.TimedTweenAction;
import com.elemengine.tweening.TweenFunction;
import com.elemengine.tweening.TweenSetter;
import org.joml.Vector2f;
import org.joml.Vector4f;

import java.util.ArrayList;

public class Animation implements Runnable
{
	private GameObject gelem;
	private ArrayList<TimedTweenAction> actions;

	public Animation(GameObject gelem)
	{
		actions = new ArrayList<>();
		this.gelem = gelem;
	}

	public ArrayList<TimedTweenAction> getActions()
	{
		return actions;
	}

	public GameObject getGelem()
	{
		return gelem;
	}

	public void setGelem(GameObject gelem)
	{
		this.gelem = gelem;
	}


	private Animation setFromToAction(TweenSetter setter, float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		actions.add(
				new TimedTweenAction(
						from,
						to,
						func, setter,
						delay,
						cycles,
						back));
		return this;
	}

	private Animation setVector2fFromToAction(TweenSetter[] setters, Vector2f from, Vector2f to, TweenFunction func, float delay, int cycles, boolean back)
	{
		actions.add(
			new TimedTweenAction(
				from.x,
				to.x,
				func, setters[0],
				delay,
				cycles,
				back));
		actions.add(
			new TimedTweenAction(
				from.y,
				to.y,
				func, setters[1],
				delay,
				cycles,
				back));
		return this;
	}


	private Animation setVector4fFromToAction(TweenSetter[] setters, Vector4f from, Vector4f to, TweenFunction func, float delay, int cycles, boolean back)
	{
		actions.add(
			new TimedTweenAction(
				from.x,
				to.x,
				func, setters[0],
				delay,
				cycles,
				back));
		actions.add(
			new TimedTweenAction(
				from.y,
				to.y,
				func, setters[1],
				delay,
				cycles,
				back));
		actions.add(
			new TimedTweenAction(
				from.z,
				to.z,
				func, setters[2],
				delay,
				cycles,
				back));
		actions.add(
			new TimedTweenAction(
				from.w,
				to.w,
				func, setters[3],
				delay,
				cycles,
				back));
		return this;
	}


	public Animation toX(float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		CommonPropertiesComponent properties = gelem.getComponent(CommonPropertiesComponent.class);
		TweenSetter setter = properties != null ? properties.xProperty::set : gelem::setX;
		return setFromToAction(setter, from, to, func, delay, cycles, back);
	}

	public Animation toY(float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		CommonPropertiesComponent properties = gelem.getComponent(CommonPropertiesComponent.class);
		TweenSetter setter = properties != null ? properties.yProperty::set : gelem::setY;
		return setFromToAction(setter, from, to, func, delay, cycles, back);
	}

	public Animation toPosition(Vector2f from, Vector2f to, TweenFunction func, float delay, int cycles, boolean back)
	{
		CommonPropertiesComponent properties = gelem.getComponent(CommonPropertiesComponent.class);
		TweenSetter[] setter = properties != null ? new TweenSetter[]{
		value -> properties.positionProperty.set(new Vector2f(value, properties.yProperty.getValue())),
		value -> properties.positionProperty.set(new Vector2f(properties.xProperty.getValue(), value))}
		: new TweenSetter[]{
		gelem::setX,
		gelem::setY};
		return setVector2fFromToAction(setter, from, to, func, delay, cycles, back);
	}

	public Animation toWidth(float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		CommonPropertiesComponent properties = gelem.getComponent(CommonPropertiesComponent.class);
		TweenSetter setter = properties != null ? properties.widthProperty::set : gelem::setWidth;
		return setFromToAction(setter, from, to, func, delay, cycles, back);
	}

	public Animation toHeight(float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		CommonPropertiesComponent properties = gelem.getComponent(CommonPropertiesComponent.class);
		TweenSetter setter = properties != null ? properties.heightProperty::set : gelem::setHeight;
		return setFromToAction(setter, from, to, func, delay, cycles, back);
	}

	public Animation toSize(Vector2f from, Vector2f to, TweenFunction func, float delay, int cycles, boolean back)
	{
		CommonPropertiesComponent properties = gelem.getComponent(CommonPropertiesComponent.class);
		TweenSetter[] setter = properties != null ? new TweenSetter[]{
				value -> properties.sizeProperty.set(new Vector2f(value, properties.heightProperty.getValue())),
				value -> properties.sizeProperty.set(new Vector2f(properties.widthProperty.getValue(), value))}
				: new TweenSetter[]{
				gelem::setWidth,
				gelem::setHeight};
		return setVector2fFromToAction(setter, from, to, func, delay, cycles, back);
	}

	public Animation toScaleX(float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		CommonPropertiesComponent properties = gelem.getComponent(CommonPropertiesComponent.class);
		TweenSetter setter = properties != null ? properties.scaleXProperty::set : gelem::setScaleX;
		return setFromToAction(setter, from, to, func, delay, cycles, back);
	}

	public Animation toScaleY(float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		CommonPropertiesComponent properties = gelem.getComponent(CommonPropertiesComponent.class);
		TweenSetter setter = properties != null ? properties.scaleYProperty::set : gelem::setScaleY;
		return setFromToAction(setter, from, to, func, delay, cycles, back);
	}

	public Animation toScale(Vector2f from, Vector2f to, TweenFunction func, float delay, int cycles, boolean back)
	{
		CommonPropertiesComponent properties = gelem.getComponent(CommonPropertiesComponent.class);
		TweenSetter[] setter = properties != null ? new TweenSetter[]{
				value -> properties.scaleProperty.set(new Vector2f(value, properties.scaleYProperty.getValue())),
				value -> properties.scaleProperty.set(new Vector2f(properties.scaleXProperty.getValue(), value))}
				: new TweenSetter[]{
				gelem::setWidth,
				gelem::setHeight};
		return setVector2fFromToAction(setter, from, to, func, delay, cycles, back);
	}

	public Animation toRotation(float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		CommonPropertiesComponent properties = gelem.getComponent(CommonPropertiesComponent.class);
		TweenSetter setter = properties != null ? properties.rotationProperty::set : gelem::setRotation;
		return setFromToAction(setter, from, to, func, delay, cycles, back);
	}

	public Animation toR(float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		CommonPropertiesComponent properties = gelem.getComponent(CommonPropertiesComponent.class);
		TweenSetter setter = properties != null ? properties.redProperty::set : gelem::setR;
		return setFromToAction(setter, from, to, func, delay, cycles, back);
	}

	public Animation toGreen(float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		CommonPropertiesComponent properties = gelem.getComponent(CommonPropertiesComponent.class);
		TweenSetter setter = properties != null ? properties.greenProperty::set : gelem::setR;
		return setFromToAction(setter, from, to, func, delay, cycles, back);
	}

	public Animation toBlue(float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		CommonPropertiesComponent properties = gelem.getComponent(CommonPropertiesComponent.class);
		TweenSetter setter = properties != null ? properties.blueProperty::set : gelem::setB;
		return setFromToAction(setter, from, to, func, delay, cycles, back);
	}

	public Animation toOpacity(float from, float to, TweenFunction func, float delay, int cycles, boolean back)
	{
		CommonPropertiesComponent properties = gelem.getComponent(CommonPropertiesComponent.class);
		TweenSetter setter = properties != null ? properties.opacityProperty::set : gelem::setOpacity;
		return setFromToAction(setter, from, to, func, delay, cycles, back);
	}

	public Animation toColor(Vector4f from, Vector4f to, TweenFunction func, float delay, int cycles, boolean back)
	{
		CommonPropertiesComponent properties = gelem.getComponent(CommonPropertiesComponent.class);
		TweenSetter[] setter = properties != null ? new TweenSetter[]{
				value -> properties.fillColorProperty.set(new Vector4f(value, properties.greenProperty.getValue(), properties.blueProperty.getValue(), properties.opacityProperty.getValue())),
				value -> properties.fillColorProperty.set(new Vector4f(properties.redProperty.getValue(), value, properties.blueProperty.getValue(), properties.opacityProperty.getValue())),
				value -> properties.fillColorProperty.set(new Vector4f(properties.redProperty.getValue(), properties.greenProperty.getValue(), value, properties.opacityProperty.getValue())),
				value -> properties.fillColorProperty.set(new Vector4f(properties.redProperty.getValue(), properties.greenProperty.getValue(), properties.blueProperty.getValue(), value))}
		: new TweenSetter[]{
				gelem::setR,
				gelem::setG,
				gelem::setB,
				gelem::setOpacity};
		return setVector4fFromToAction(setter, from, to, func, delay, cycles, back);
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

	public boolean isSleep()
	{
		for(var all : actions)
		{
			if(!all.isSleep())
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
