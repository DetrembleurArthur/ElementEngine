package com.elemengine.graphics.loaders;

import com.elemengine.graphics.vertex.Mesh;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.util.ArrayList;

public class ObjData
{
	private String name;
	private ArrayList<Vector3f> positions;
	private ArrayList<Vector2f> textureCoords;
	private ArrayList<Vector3f> normals;
	private ArrayList<Vector3i> faces;

	public ObjData()
	{
		positions = new ArrayList<>();
		textureCoords = new ArrayList<>();
		normals = new ArrayList<>();
		faces = new ArrayList<>();
	}

	public ArrayList<Vector3f> getPositions()
	{
		return positions;
	}

	public void setPositions(ArrayList<Vector3f> positions)
	{
		this.positions = positions;
	}

	public ArrayList<Vector2f> getTextureCoords()
	{
		return textureCoords;
	}

	public void setTextureCoords(ArrayList<Vector2f> textureCoords)
	{
		this.textureCoords = textureCoords;
	}

	public ArrayList<Vector3f> getNormals()
	{
		return normals;
	}

	public void setNormals(ArrayList<Vector3f> normals)
	{
		this.normals = normals;
	}

	public ArrayList<Vector3i> getFaces()
	{
		return faces;
	}

	public void setFaces(ArrayList<Vector3i> faces)
	{
		this.faces = faces;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Mesh extractMesh()
	{
		float[] vertices = new float[faces.size() * (3 + 2)];
		int[] indices = new int[faces.size()];
		int i = 0;
		for(Vector3i face : faces)
		{
			vertices[i++] = positions.get(face.x - 1).x;
			vertices[i++] = positions.get(face.x - 1).y;
			vertices[i++] = positions.get(face.x - 1).z;
			vertices[i++] = textureCoords.get(face.y - 1).x;
			vertices[i++] = textureCoords.get(face.y - 1).y;
		}
		for(i = 0; i < indices.length; i++) indices[i] = i;
		return new Mesh(vertices, indices, Mesh.DIMENSION_3, Mesh.TEXTURED);
	}

	public void show()
	{
		System.out.println("NAME: " + name);
		System.out.println("POSITIONS:");
		for(var p : positions)
		{
			System.out.println(p);
		}
		System.out.println("TEXTURE COORDS:");
		for(var tc : textureCoords)
		{
			System.out.println(tc);
		}
		System.out.println("NORMALS:");
		for(var n : normals)
		{
			System.out.println(n);
		}
		System.out.println("FACES:");
		for(var f : faces)
		{
			System.out.println(f);
		}
	}
}
