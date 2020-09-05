/*import game.jgengine.debug.Logs;
import game.jgengine.entity.Dynamic;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.camera.Camera3D;
import game.jgengine.graphics.camera.PerspProjectionSettings;
import game.jgengine.graphics.gui.smart.SmartRectangle;
import game.jgengine.graphics.rendering.TargetRenderer;
import game.jgengine.graphics.rendering.TargetTexture;
import game.jgengine.graphics.shaders.Shader;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Scene2D;
import game.jgengine.sys.Window;
import game.jgengine.utils.Colors;
import org.joml.Vector2i;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;

public class Rotating3DItem extends Scene2D
{

	ArrayList<Dynamic> list = new ArrayList<>();

	SmartRectangle item;
	SmartRectangle box;
	TargetRenderer trenderer;
	TargetTexture ttexture;
	Camera3D cam3d;

	Rectangle black;

	@Override
	public void load()
	{

	}


	@Override
	public void loadResources()
	{
		item = new SmartRectangle(Registry.getTexture("bricks"));
		item.getShape().setFillColor(Colors.LIME);
		item.getShape().setSize(1f, 1f);
		item.getShape().setPosition(new Vector3f(0, 0, 0));
		item.getShape().setCenterOrigin();

		cam3d = new Camera3D(new PerspProjectionSettings(70, 1, 0.001f, 1000f));
		ttexture = new TargetTexture(new Vector2i(300, 300));
		trenderer = new TargetRenderer(Shader.DEFAULT, cam3d, ttexture);

		box = new SmartRectangle(ttexture.getTexture());
		box.getShape().setPosition(0, 0);



		list.add(box);
		Logs.print("Resources loaded");

		black = new Rectangle(null);
		black.setSize(2, 2);
		black.setFillColor(new Vector4f(0, 0, 0, 0.7f));
		black.setPosition(-1, -1);
	}

	@Override
	public void update(double dt)
	{
		getCamera2d().activateKeys(Window.WINDOW, Camera2D.SPECTATOR_KEY_SET);
		item.getShape().getRotation().y += 1;
		for(var o : list) o.run();
		box.positionProperty.set(Mouse.getPosition(getCamera2d()));

	}

	@Override
	public void render(double dt)
	{
		trenderer.render(black);
		trenderer.render(item.getShape());
		for(var g : list)
		{
			draw(g);
		}
	}

	@Override
	public void close()
	{

	}

	@Override
	public void closeResources()
	{
		for(var g : list)
		{
			g.destroy();
		}
	}

	@Override
	public void keyPressedEventHandler(int key)
	{
		if(key == GLFW.GLFW_KEY_LEFT_CONTROL)
		{
			Window.WINDOW
					.takeScreenShot(
					("demo_screenshots/" +
							Date.from(Instant.now()) + ".png")
					.replace(" ", "_")
					.replace(":", "-"));
		}
		else
		{



		}
	}
}
*/