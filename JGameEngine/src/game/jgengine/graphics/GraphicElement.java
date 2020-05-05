package game.jgengine.graphics;

import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;

public class GraphicElement extends Transformable
{
	private Mesh mesh;
	private int primitive;

	public int getPrimitive()
	{
		return primitive;
	}

	public void setPrimitive(int primitive)
	{
		this.primitive = primitive;
	}

	public GraphicElement(Mesh mesh)
	{
		super(new Vector3f(), new Vector3f(),  new Vector3f(1,1,1));
		this.mesh = mesh;
		primitive = GL_TRIANGLES;
	}
	public GraphicElement(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh, int primitive)
	{
		super(position, rotation, scale);
		this.mesh = mesh;
		this.primitive = primitive;
	}

	public void draw()
	{
		mesh.getVertexArray().bind();
		mesh.getVertexArray().enableAttribs();
		mesh.getIndexBuffer().drawElements(primitive);
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
