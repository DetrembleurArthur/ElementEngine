package game.jgengine.graphics.shaders;

import game.jgengine.graphics.Mesh;
import game.jgengine.graphics.vertex.GraphicElement;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;

public class Textured extends GraphicElement
{
	private Texture texture;

	public Textured(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh, String path, boolean rgba)
	{
		super(position, rotation, scale, mesh, GL_TRIANGLES);
		texture = new Texture(path, rgba);
	}

	public Texture getTexture()
	{
		return texture;
	}

	@Override
	public void destroy()
	{
		super.destroy();
		texture.destroy();
	}
}
