package game.jgengine.event;

import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.sys.Window;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector2f;
import org.joml.Vector4f;

import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPos;

public class Mouse
{

	public static Vector2f getPosition()
	{
		double[] x = new double[1], y = new double[1];
		glfwGetCursorPos(Window.WINDOW.getId(), x, y);
		return new Vector2f(
				(float)x[0],
				(float)y[0]);
	}

	public static Vector2f getPosition(@NotNull Camera2D camera)
	{
		var pos = getPosition().sub(
						Window.WINDOW.getSize().sub(Window.WINDOW.getAspectRatioSize()).div(new Vector2f(2, 2))
		).div(new Vector2f(
				Window.WINDOW.getAspectRatioSize().x,
				-Window.WINDOW.getAspectRatioSize().y
		)).mul(2).sub(new Vector2f(1, -1));
		var tmp = new Vector4f(pos.x, pos.y, 0, 1).mul(camera.getInvProjectionMatrix()).mul(camera.getInvViewMatrix());
		pos.x = tmp.x;
		pos.y = tmp.y;

		return pos;
	}


	@NotNull
	public static Vector2f getIPosition()
	{
		double[] x = new double[1], y = new double[1];
		glfwGetCursorPos(Window.WINDOW.getId(), x, y);
		return new Vector2f((int)x[0], (int)y[0]);
	}

	public static  void setPosition(double x, double y)
	{
		glfwSetCursorPos(Window.WINDOW.getId(), x, y);
	}

	public static  void setPosition(@NotNull Vector2f pos)
	{
		glfwSetCursorPos(Window.WINDOW.getId(), pos.x, pos.y);
	}

}
