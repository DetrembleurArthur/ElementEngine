import game.jgengine.debug.Logs;
import game.jgengine.entity.GameObject;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.camera.Camera3D;
import game.jgengine.graphics.loaders.ObjLoader;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Game;
import game.jgengine.sys.Scene3D;
import game.jgengine.sys.Window;
import game.jgengine.tweening.TimedTweenAction;
import game.jgengine.tweening.TweenFunctions;
import game.jgengine.utils.Colors;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.Random;
import java.util.RandomAccess;

public class FpsScene extends Scene3D
{
	ArrayList<GameObject> gobjects;
	TimedTweenAction action;

	@Override
	public void load()
	{
		Window.WINDOW.setClearColor(Colors.BLACK);
		Window.WINDOW.disableCursor();
		centered = true;
		action.start();
	}

	@Override
	public void update(double dt)
	{
		getCamera3D().activateKeys(Camera3D.SPECTATOR_KEY_SET, Window.WINDOW);
		for(var o : gobjects)
		{
			o.rotate(1, 2, 0.5f);
		}
		action.run();
	}

	@Override
	public void render(double dt)
	{
		for(var o : gobjects)
		{
			draw(o);
		}
	}

	@Override
	public void close()
	{
		Window.WINDOW.resetCursor();
		action.stop();
	}

	@Override
	public void buttonPressedEventHandler(int button)
	{
		Game.GAME.setCurrentScene("MENU");
	}

	@Override
	public void loadResources()
	{
		gobjects = new ArrayList<>();
		var gen = new Random();
		for(int i = 0; i < 100; i++)
		{
			GameObject obj = new GameObject(
					ObjLoader.loadModel("assets/3Dmodels/cube.obj").extractMesh(),
					null);
			obj.setFillColor(Colors.random());
			obj.setPosition(new Vector3f(
					gen.nextFloat() * -300 + 150,
					gen.nextFloat() * -300 + 150,
					gen.nextFloat() * -300 + 150));
			gobjects.add(obj);
		}

		action = new TimedTweenAction(1, 20, TweenFunctions.EASE_IN_OUT_BOUNCE,
				(x) -> {
			for(var o : gobjects) o.setScale(new Vector3f(x, x, x));
				}, 5000, TimedTweenAction.INFINITE_CYCLE, true);
		getCamera3D().setMoveSpeed(1f);
	}

	@Override
	public void closeResources()
	{
		for(var o : gobjects)
			o.destroy();
		gobjects.clear();
	}

	@Override
	public void keyPressedEventHandler(int key)
	{
		super.keyPressedEventHandler(key);
		if(key == GLFW.GLFW_KEY_SPACE)
			Window.WINDOW.takeScreenShot("assets/screen.png");
	}
}
