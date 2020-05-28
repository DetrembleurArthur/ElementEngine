package game.jgengine.registry;

import game.jgengine.graphics.shaders.Shader;
import game.jgengine.graphics.shaders.Texture;

import java.util.HashMap;

public class Registry
{
	private static HashMap<String, Texture> textures;
	private static HashMap<String, Shader> shaders;
	static
	{
		textures = new HashMap<>();
		shaders = new HashMap<>();
	}

	public static void set(String name, Texture texture)
	{
		textures.put(name, texture);
	}

	public static Texture getTexture(String name)
	{
		return textures.get(name);
	}

	public static void set(String name, Shader shader)
	{
		shaders.put(name, shader);
	}

	public static Shader getShader(String name)
	{
		return shaders.get(name);
	}

	public static void close()
	{
		for(var t : textures.keySet())
		{
			textures.get(t).destroy();
		}
		for(var s : shaders.keySet())
		{
			shaders.get(s).destroy();
		}
	}
}
