package game.jgengine.graphics.vertex;

import game.jgengine.graphics.Camera;
import game.jgengine.graphics.Mesh;
import game.jgengine.graphics.Transformable;
import game.jgengine.graphics.shaders.Shader;
import game.jgengine.utils.Color;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class GraphicElement extends Transformable
{
	private Mesh mesh;
	private int drawType;

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
		super(position, rotation, scale);
		this.mesh = mesh;
		this.drawType = drawType;
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

	public void destroy()
	{
		mesh.destroy();
	}
}
