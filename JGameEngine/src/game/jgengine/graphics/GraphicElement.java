package game.jgengine.graphics;

import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.*;

public class GraphicElement extends Transformable
{
	private Mesh mesh;
	private int primitive;
	private int lineWeight = 1;

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
		if(isRenderLine()) glLineWidth(lineWeight);
		mesh.getVertexArray().bind();
		mesh.getVertexArray().enableAttribs();
		mesh.getIndexBuffer().drawElements(primitive);
		mesh.getVertexArray().disableAttribs();
		mesh.getVertexArray().unbind();
		if(isRenderLine()) glLineWidth(0);
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
