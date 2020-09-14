package tests;

import game.jgengine.event.Input;
import game.jgengine.event.Mouse;
import game.jgengine.graphics.shapes.Circle;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.scripting.Script;
import game.jgengine.sys.Scene2D;
import game.jgengine.sys.Window;
import game.jgengine.time.SyncTimer;
import game.jgengine.tweening.TFunc;
import game.jgengine.utils.Colors;
import game.jgengine.utils.DynamicLaterList;
import game.jgengine.utils.MathUtil;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;


public class MainScene extends Scene2D
{

	DynamicLaterList list = new DynamicLaterList();

	Rectangle player;



	@Override
	public void load()
	{
		Window.WINDOW.setClearColor(Colors.BLACK);
		Rectangle.setCommonModel(true);
	}


	@Override
	public void loadResources()
	{

		player = new Rectangle();
		player.setPosition(Window.WINDOW.getCenter());
		player.setCenterOrigin();
		player.setSize(50, 100);
		player.moves_c();
		player.scripts_c().add(new Script().addAction(() -> {
			player.moves_c().setSpeed(new Vector2f());
			if(Input.isKeyPressed(Window.WINDOW, GLFW.GLFW_KEY_UP))
			{
				player.moves_c().getSpeed().y = -400;
			}
			if(Input.isKeyPressed(Window.WINDOW, GLFW.GLFW_KEY_DOWN))
			{
				player.moves_c().getSpeed().y = 400;
			}
			if(Input.isKeyPressed(Window.WINDOW, GLFW.GLFW_KEY_RIGHT))
			{
				player.moves_c().getSpeed().x = 400;
			}
			if(Input.isKeyPressed(Window.WINDOW, GLFW.GLFW_KEY_LEFT))
			{
				player.moves_c().getSpeed().x = -400;
			}
			if(Input.isLeftButtonPressed(Window.WINDOW))
			{
				Rectangle rectangle = new Rectangle();
				rectangle.setSize(50, 50);
				rectangle.setFillColor(Colors.random());
				rectangle.setCenterOrigin();
				rectangle.setPosition(player.getPosition2D());
				rectangle.moves_c().speedToward(Mouse.getPosition(getCamera2d()), new Vector2f(1000, 1000));
				rectangle.moves_c().accelerationToward(Mouse.getPosition(getCamera2d()), new Vector2f(100, 100));
				rectangle.timers_c().add(new SyncTimer(500, 1, () -> {
					rectangle.timers_c().add(new SyncTimer(505, 1, () -> {
						var frags = rectangle.getFragments(2, 2);
						for(var f : frags)
						{
							f.animations_c();
							f.setFillColor(rectangle.getFillColor());
							f.moves_c().setRotationSpeed(MathUtil.rand(360, -360));
							f.moves_c().setSpeed(new Vector2f(MathUtil.rand(1000, -1000),MathUtil.rand(1000, -1000)));

							var an = f.animations_c().create("test");
							an.toOpacity(1, 0, TFunc.EASE_IN_OUT_QUAD, 1500, 1, false);
							f.animations_c().startAnimation("test");
							f.timers_c().add(new SyncTimer(1500, 1, () -> {
								f.destroy();
								list.removeLater(f);
							}));

						}
						rectangle.destroy();
						list.addLater(frags);
						list.removeLater(rectangle);
					}));
				}));
				list.addLater(rectangle);
			}
		}));

		list.add(player);

	}

	@Override
	public void update(double dt)
	{
		//getCamera2d().activateKeys(Window.WINDOW, Camera2D.SPECTATOR_KEY_SET);

		list.run();

	}

	@Override
	public void render(double dt)
	{

		list.draw(defaultRenderer);
	}

	@Override
	public void close()
	{

	}

	@Override
	public void closeResources()
	{
		list.destroy();
	}



	@Override
	public void keyPressedEventHandler(int key)
	{
		if(key == GLFW.GLFW_KEY_LEFT_CONTROL)
		{
			Window.WINDOW.takeScreenShot();
		}
		else
		{




		}
	}

	@Override
	public void buttonPressedEventHandler(int button)
	{
		if(button == Mouse.Button.RIGHT)
			getCamera2d().focus(Mouse.getPosition(getCamera2d()));
	}
}
