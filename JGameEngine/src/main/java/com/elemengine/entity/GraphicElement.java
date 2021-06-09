package com.elemengine.entity;

import com.elemengine.graphics.rendering.Renderer;
import com.elemengine.graphics.rendering.Sprite;
import com.elemengine.graphics.rendering.Texture;
import com.elemengine.graphics.vertex.Mesh;
import com.elemengine.utils.Colors;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import static org.lwjgl.opengl.GL11.*;

public class GraphicElement extends Transformable implements Dynamic
{
	private Mesh mesh;
	private int primitive;
	private int lineWeight = 1;
	private Vector4f fillColor = new Vector4f(Colors.WHITE);

	private Texture texture;

	public GraphicElement(Mesh mesh)
	{
		this(mesh, null);
	}

	public GraphicElement(Mesh mesh, Texture texture)
	{
		super(new Vector3f(), new Vector3f(),  new Vector3f(1,1,1));
		this.mesh = mesh;
		primitive = GL_TRIANGLES;
		setTexture(texture);
	}
	public GraphicElement(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh, Texture texture, int primitive)
	{
		super(position, rotation, scale);
		this.mesh = mesh;
		this.primitive = primitive;
		setTexture(texture);
	}

	public void setSprite(Sprite sprite)
	{
		for(int i = 0; i < sprite.getTextCoords().length; i++)
		{
			getMesh().setUV(i, sprite.getTextCoords()[i]);
		}
	}

	public int getLineWeight()
	{
		return lineWeight;
	}

	public void setLineWeight(int lineWeight)
	{
		this.lineWeight = lineWeight;
	}

	public int getPrimitive()
	{
		return primitive;
	}

	public void setPrimitive(int primitive)
	{
		this.primitive = primitive;
	}

	public void setPointsRenderMode()
	{
		setPrimitive(GL_POINTS);
	}

	public void setTriangleRenderMode()
	{
		setPrimitive(GL_TRIANGLES);
	}

	public void setTriangleStripRenderMode()
	{
		setPrimitive(GL_TRIANGLE_STRIP);
	}

	public void setTriangleFanRenderMode()
	{
		setPrimitive(GL_TRIANGLE_FAN);
	}

	public void setLineLoopRenderMode()
	{
		setPrimitive(GL_LINE_LOOP);
	}

	public void setLinesRenderMode()
	{
		setPrimitive(GL_LINES);
	}

	public void setLineStripRenderMode()
	{
		setPrimitive(GL_LINE_STRIP);
	}

	public boolean isRenderLine()
	{
		return primitive > 0 && primitive < GL_TRIANGLES;
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
			if(isRenderLine()) glLineWidth(lineWeight);
			mesh.getVertexArray().bind();
			mesh.getVertexArray().enableAttribs();
			mesh.getIndexBuffer().drawElements(primitive);
			mesh.getVertexArray().disableAttribs();
			mesh.getVertexArray().unbind();
			if(isRenderLine()) glLineWidth(0);
			texture.unbind();
		}
		else
		{
			if(isRenderLine()) glLineWidth(lineWeight);
			mesh.getVertexArray().bind();
			mesh.getVertexArray().enableAttribs();
			mesh.getIndexBuffer().drawElements(primitive);
			mesh.getVertexArray().disableAttribs();
			mesh.getVertexArray().unbind();
			if(isRenderLine()) glLineWidth(0);
		}
	}


	public Mesh getMesh()
	{
		return mesh;
	}

	public void setMesh(Mesh mesh)
	{
		if(this.mesh != null)
		{
			destroy();
		}
		this.mesh = mesh;
	}

	public void destroy()
	{
		mesh.destroy();
		mesh = null;
	}

	public boolean isDefined()
	{
		return mesh != null;
	}

	public void setFillColor(Vector4f color)
	{
		this.fillColor = new Vector4f(color);
	}

	public void setFillColorKeep(Vector4f color)
	{
		this.fillColor = color;
	}

	public void setR(float r)
	{
		this.fillColor.x = r;
	}

	public void setG(float g)
	{
		this.fillColor.y = g;
	}

	public void setB(float b)
	{
		this.fillColor.z = b;
	}

	public void setA(float a)
	{
		this.fillColor.w = a;
	}

	public float getR()
	{
		return this.fillColor.x;
	}

	public float getG()
	{
		return this.fillColor.y;
	}

	public float getB()
	{
		return this.fillColor.z;
	}

	public float getA()
	{
		return this.fillColor.w;
	}

	public Vector4f getFillColor()
	{
		return new Vector4f(fillColor);
	}

	public void setOpacity(float value)
	{
		if(fillColor != null)
			fillColor.w = value;
	}

	public float getOpacity()
	{
		return fillColor.w;
	}

	public void draw(Renderer renderer)
	{
		renderer.render(this);
	}

}
