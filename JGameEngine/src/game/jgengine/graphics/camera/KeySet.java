package game.jgengine.graphics.camera;

import java.util.HashMap;

public class KeySet extends HashMap<String, Integer>
{
	public KeySet()
	{

	}

	public KeySet set(String id, int glfwKeyId)
	{
		put(id, glfwKeyId);
		return this;
	}
}
