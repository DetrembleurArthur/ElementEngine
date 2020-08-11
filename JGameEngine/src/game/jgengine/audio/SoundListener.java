package game.jgengine.audio;

import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.openal.AL10.*;

public class SoundListener
{
	public SoundListener() {
		this(new Vector3f(0, 0, 0));
	}

	public SoundListener(Vector3f position) {
		alListener3f(AL_POSITION, position.x, position.y, position.z);
		alListener3f(AL_VELOCITY, 0, 0, 0);
	}

	public void setSpeed(Vector3f speed) {
		alListener3f(AL_VELOCITY, speed.x, speed.y, speed.z);
	}

	public void setPosition(Vector3f position) {
		alListener3f(AL_POSITION, position.x, position.y, position.z);
	}

	public void setPosition(Vector2f position)
	{
		setPosition(new Vector3f(position.x, position.y, 0));
	}

	public void setOrientation(Vector2f at, Vector2f up) {
		float[] data = new float[6];
		data[0] = at.x;
		data[1] = at.y;
		data[2] = 0;
		data[3] = up.x;
		data[4] = up.y;
		data[5] = 0;
		alListenerfv(AL_ORIENTATION, data);
	}
}
