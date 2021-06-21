package com.elemengine.graphics.loaders;

import org.joml.Vector3f;
import org.joml.Vector3i;

import java.util.ArrayList;

public class TriangularFace
{
	private ArrayList<Vector3i> vertices;

	public TriangularFace()
	{
		this.vertices = new ArrayList<>();
	}

	public ArrayList<Vector3i> getVertices()
	{
		return vertices;
	}

	public void setVertices(ArrayList<Vector3i> vertices)
	{
		this.vertices = vertices;
	}
}
