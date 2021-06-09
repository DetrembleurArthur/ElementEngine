package com.elemengine.components.properties;

import com.elemengine.binding.FloatProperty;
import com.elemengine.binding.Property;
import com.elemengine.components.Component;
import com.elemengine.entity.GameObject;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class CommonPropertiesComponent extends Component
{
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
			return getRelativeObject().getPosition2D();
		}
	};

	public final FloatProperty xProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			getRelativeObject().setX(value);
		}

		@Override
		public Float getValue()
		{
			return getRelativeObject().getX();
		}
	};

	public final FloatProperty yProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			getRelativeObject().setY(value);
		}

		@Override
		public Float getValue()
		{
			return getRelativeObject().getY();
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
			return getRelativeObject().getSize2D();
		}
	};

	public final FloatProperty widthProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			getRelativeObject().setWidth(value);
		}

		@Override
		public Float getValue()
		{
			return getRelativeObject().getWidth();
		}
	};

	public final FloatProperty heightProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			getRelativeObject().setHeight(value);
		}

		@Override
		public Float getValue()
		{
			return getRelativeObject().getHeight();
		}
	};

	public final FloatProperty rotationProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			getRelativeObject().setRotation(value);
		}

		@Override
		public Float getValue()
		{
			return getRelativeObject().getRotation2D();
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
			return getRelativeObject().getFillColor();
		}
	};

	public final FloatProperty redProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			getRelativeObject().setR(value);
		}

		@Override
		public Float getValue()
		{
			return getRelativeObject().getR();
		}
	};

	public final FloatProperty greenProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			getRelativeObject().setG(value);
		}

		@Override
		public Float getValue()
		{
			return getRelativeObject().getG();
		}
	};

	public final FloatProperty blueProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			getRelativeObject().setB(value);
		}

		@Override
		public Float getValue()
		{
			return getRelativeObject().getB();
		}
	};

	public final FloatProperty opacityProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			getRelativeObject().setOpacity(value);
		}

		@Override
		public Float getValue()
		{
			return getRelativeObject().getOpacity();
		}
	};

	public final FloatProperty scaleXProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			getRelativeObject().setScaleX(value);
		}

		@Override
		public Float getValue()
		{
			return getRelativeObject().getScaleX();
		}
	};

	public final FloatProperty scaleYProperty = new FloatProperty()
	{
		@Override
		public void setValue(Float value)
		{
			getRelativeObject().setScaleY(value);
		}

		@Override
		public Float getValue()
		{
			return getRelativeObject().getScaleY();
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
			return getRelativeObject().getScale2D();
		}
	};

	public CommonPropertiesComponent(GameObject relativeObject)
	{
		super(relativeObject);
	}
}
