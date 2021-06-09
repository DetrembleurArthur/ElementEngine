package com.elemengine.graphics.loaders;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.io.*;

public class ObjLoader
{
	public static ObjData loadModel(String filename)
	{
		File file = new File(filename);
		if(file.exists())
		{
			try
			{
				ObjData objData = new ObjData();
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String buffer;
				while((buffer = reader.readLine()) != null)
				{
					var tokens = buffer.split(" ");
					if(tokens[0].equals("o"))
					{
						objData.setName(tokens[1]);
					}
					else if(tokens[0].equals("v"))
					{
						objData.getPositions().add(new Vector3f(
								Float.parseFloat(tokens[1]),
								Float.parseFloat(tokens[2]),
								Float.parseFloat(tokens[3])));
					}
					else if(tokens[0].equals("vt"))
					{
						objData.getTextureCoords().add(new Vector2f(
								Float.parseFloat(tokens[1]),
								Float.parseFloat(tokens[2])));
					}
					else if(tokens[0].equals("vn"))
					{
						objData.getNormals().add(new Vector3f(
								Float.parseFloat(tokens[1]),
								Float.parseFloat(tokens[2]),
								Float.parseFloat(tokens[3])));
					}
					else if(tokens[0].equals("f"))
					{
						for(int i = 1; i < tokens.length; i++)
						{
							var tf = tokens[i].split("/");
							objData.getFaces().add(new Vector3i(Integer.parseInt(tf[0]), Integer.parseInt(tf[1]), Integer.parseInt(tf[2])));
						}
					}
				}
				reader.close();
				return objData;
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
}
