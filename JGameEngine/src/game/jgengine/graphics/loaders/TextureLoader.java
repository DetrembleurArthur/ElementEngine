package game.jgengine.graphics.loaders;

import game.jgengine.graphics.shaders.Texture;
import game.jgengine.registry.Registry;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public class TextureLoader
{
	private static HashMap<String, Boolean> formats;

	static
	{
		formats = new HashMap<>();
		formats.put("png", true);
		formats.put("jpg", false);
	}

	public static Texture load(String regid, String filepath)
	{
		String format;
		var temp = filepath.split("\\.");
		if(temp.length >= 2)
		{
			format = temp[temp.length-1];
			if(formats.containsKey(format))
			{
				Texture texture = new Texture(filepath, formats.get(format));
				Registry.set(regid, texture);
				return texture;
			}
		}
		return null;
	}

	public static void loadDir(String directory)
	{
		File dir = new File(directory);
		for(String filepath : Objects.requireNonNull(dir.list()))
		{
			load(filepath, directory + "/"+filepath);
		}
	}
}
