package com.elemengine.utils;

import org.joml.Vector4f;
import org.joml.Vector4i;

public class To
{
	public static Vector4i Vector4i(Vector4f v)
	{
		return new Vector4i((int)v.x, (int)v.y, (int)v.z, (int)v.w);
	}
}
