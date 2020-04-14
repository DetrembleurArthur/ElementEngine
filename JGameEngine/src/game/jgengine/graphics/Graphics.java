package game.jgengine.graphics;

import game.jgengine.sys.Window;
import game.jgengine.utils.Color;
import game.jgengine.utils.Colors;
import game.jgengine.utils.DPoint2D;
import game.jgengine.utils.Size2D;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLUtil;
import org.lwjgl.system.MemoryUtil;

import java.awt.geom.Point2D;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.glfw.GLFW.glfwGetCurrentContext;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Graphics
{
	public static void drawLine(DPoint2D p1, DPoint2D p2, Color color)
	{
		Size2D size = Window.currentWindow.getSize();

		glBegin(GL_LINES);
		new VertexArray(new Vertex[]{
				new VertexC2D(p1.getX(), size.getHeight()-p1.getY(), Colors.MAGENTA),
				new VertexC2D(p2.getX(), size.getHeight()-p2.getY(), Colors.LIME)
		}).draw();
		glEnd();
	}
}
