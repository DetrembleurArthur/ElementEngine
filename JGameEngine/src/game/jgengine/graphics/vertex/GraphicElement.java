package game.jgengine.graphics.vertex;

import game.jgengine.graphics.Camera;
import game.jgengine.graphics.Mesh;
import game.jgengine.graphics.shaders.Shader;
import game.jgengine.utils.Color;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class GraphicElement
{
	private Mesh mesh;
	private int drawType;
	private Vector3f position, rotation, scale;

	public int getDrawType()
	{
		return drawType;
	}

	public void setDrawType(int drawType)
	{
		this.drawType = drawType;
	}

	public GraphicElement(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh, int drawType)
	{
		this.mesh = mesh;
		this.drawType = drawType;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}

	public void draw()
	{
		mesh.getVertexArray().bind();
		mesh.getVertexArray().enableAttribs();
		mesh.getIndexBuffer().drawElements(drawType);
		mesh.getVertexArray().disableAttribs();
		mesh.getVertexArray().unbind();
	}

	public Mesh getMesh()
	{
		return mesh;
	}

	public void setMesh(Mesh mesh)
	{
		this.mesh = mesh;
	}

	public Vector3f getPosition()
	{
		return position;
	}

	public void setPosition(Vector3f position)
	{
		this.position = position;
	}

	public Vector3f getRotation()
	{
		return rotation;
	}

	public void setRotation(Vector3f rotation)
	{
		this.rotation = rotation;
	}

	public Vector3f getScale()
	{
		return scale;
	}

	public void setScale(Vector3f scale)
	{
		this.scale = scale;
	}

	public Matrix4f getTransformMatrix()
	{
		Matrix4f result = new Matrix4f().identity();

		Matrix4f translationMatrix = new Matrix4f().translate(position);
		Matrix4f rotXMatrix = new Matrix4f().rotate(rotation.x, new Vector3f(1, 0, 0));
		Matrix4f rotYMatrix = new Matrix4f().rotate(rotation.y, new Vector3f(0, 1, 0));
		Matrix4f rotZMatrix = new Matrix4f().rotate(rotation.z, new Vector3f(0, 0, 1));
		Matrix4f scaleMatrix = new Matrix4f().scale(scale);

		Matrix4f rotationMatrix = rotXMatrix.mul(rotYMatrix.mul(rotZMatrix));
		result = translationMatrix.mul(rotationMatrix.mul(scaleMatrix));

		return result;
	}

	public void destroy()
	{
		mesh.destroy();
	}
}
