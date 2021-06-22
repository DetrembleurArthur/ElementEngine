package com.elemengine.registry;

import com.elemengine.graphics.rendering.Texture;
import com.elemengine.debug.Log;
import com.elemengine.graphics.shaders.Shader;
import com.elemengine.graphics.texts.fnt.Font;
import com.elemengine.graphics.texts.fnt.FontSet;

import java.util.HashMap;

public class Registry
{
	private static final HashMap<String, Texture> textures;
	private static final HashMap<String, Shader> shaders;
	private static final HashMap<String, Font> fonts;
	private static final HashMap<String, FontSet> fontSets;
	static
	{
		textures = new HashMap<>();
		shaders = new HashMap<>();
		fonts = new HashMap<>();
		fontSets = new HashMap<>();
	}

	public static void set(String name, Texture texture)
	{
		textures.put(name, texture);
		Log.print("Texture set: " + name);
	}

	public static Texture getTexture(String name)
	{
		return textures.get(name);
	}

	public static void set(String name, Shader shader)
	{
		shaders.put(name, shader);
		Log.print("Shader set: " + name);
	}

	public static Shader getShader(String name)
	{
		return shaders.get(name);
	}

	public static void set(String name, Font font)
	{
		fonts.put(name, font);
		Log.print("Font set: " + name);
	}

	public static Font getFont(String name)
	{
		return fonts.get(name);
	}

	public static void set(String name, FontSet fontSet)
	{
		fontSets.put(name, fontSet);
		Log.print("FontSet set: " + name);
	}

	public static FontSet getFontSet(String name)
	{
		return fontSets.get(name);
	}

	public static void close()
	{
		for(var t : textures.keySet())
		{
			textures.get(t).destroy();
			Log.print("Texture free: " + t);
		}
		for(var s : shaders.keySet())
		{
			shaders.get(s).destroy();
			Log.print("Shader free: " + s);
		}
	}
}
