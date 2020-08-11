import game.jgengine.audio.SoundBuffer;
import game.jgengine.audio.SoundManager;
import game.jgengine.audio.SoundSource;
import game.jgengine.binding.Property;
import game.jgengine.debug.Logs;
import game.jgengine.event.Input;
import game.jgengine.graphics.camera.Camera2D;
import game.jgengine.graphics.camera.OrthoProjectionSettings;
import game.jgengine.graphics.gui.event.Event;
import game.jgengine.graphics.gui.event.MouseButtonDoubleClickEvent;
import game.jgengine.graphics.gui.event.MouseLeftButtonClickEvent;
import game.jgengine.graphics.gui.event.ValueChangedEvent;
import game.jgengine.graphics.gui.widgets.*;
import game.jgengine.graphics.rendering.TargetRenderer;
import game.jgengine.graphics.rendering.TargetTexture;
import game.jgengine.graphics.shaders.Shader;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.graphics.shapes.Slider;
import game.jgengine.registry.Registry;
import game.jgengine.sys.Scene2D;
import game.jgengine.sys.Window;
import game.jgengine.tweening.TweenAction;
import game.jgengine.tweening.TweenFunctions;
import game.jgengine.utils.Colors;
import game.jgengine.utils.To;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

import javax.sound.midi.spi.SoundbankReader;
import javax.swing.*;
import java.sql.Date;
import java.time.Instant;

public class MainScene extends Scene2D
{
	SoundBuffer soundBuffer;
	SoundSource soundSource;



	@Override
	public void load()
	{

	}


	int i = 0;
	@Override
	public void loadResources()
	{
		soundBuffer = new SoundBuffer("assets/audio/test.ogg");
		soundSource = new SoundSource(false, true);
		soundSource.setBuffer(soundBuffer.getId());
		soundSource.setGain(100);

	}

	@Override
	public void update(double dt)
	{
		getCamera2d().activateKeys(Window.WINDOW, Camera2D.SPECTATOR_KEY_SET);
		SoundManager.getListener().setPosition(getCamera2d().getPosition());
		Logs.print(getCamera2d().getPosition());

	}

	@Override
	public void render(double dt)
	{

	}

	@Override
	public void close()
	{

	}

	@Override
	public void closeResources()
	{
		soundBuffer.destroy();
		soundSource.destroy();
	}

	@Override
	public void keyPressedEventHandler(int key)
	{
		if(key == GLFW.GLFW_KEY_LEFT_CONTROL)
		{
			Logs.print("SCREENSHOT TOOK");
			Window.WINDOW
					.takeScreenShot(
					("demo_screenshots/" +
							Date.from(Instant.now()) + ".png")
					.replace(" ", "_")
					.replace(":", "-"));
		}
		else
		{

			soundSource.play();
		}
	}
}
