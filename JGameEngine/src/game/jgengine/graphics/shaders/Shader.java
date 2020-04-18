package game.jgengine.graphics.shaders;

import game.jgengine.graphics.shapes.Shape;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;

public class Shader
{
	public static final Shader DEFAULT = new Shader(
			"#version 330 core\n" + "layout (location=0) in vec3 aPos;\n" + "layout (location=1) in vec4 aColor;\n" + "out vec4 fColor;\n" + "void main()\n" + "{\n" + "fColor = aColor;\n" + "gl_Position = vec4(aPos, 1);\n" + "}",
			"#version 330 core\n" + "in vec4 fColor;\n" + "out vec4 color;\n" + "void main()\n" + "{\n" + "color = fColor;\n" + "}\n",
			false
	);

	private int vertexShader = -1;
	private int fragmentShader = -1;
	private int program = -1;
	private HashMap<String, Integer> uniforms = new HashMap<>();

	public Shader()
	{

	}

	public Shader(String vertexSrc, String fragmentSrc, boolean areFile)
	{
		if(areFile)
		{
			initFromFile(vertexSrc, fragmentSrc);
		}
		else
		{
			init(vertexSrc, fragmentSrc);
		}
	}

	public void init(String vertexSrc, String fragmentSrc)
	{
		vertexShader = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(vertexShader, vertexSrc);
		glCompileShader(vertexShader);

		if(glGetShaderi(vertexShader, GL_COMPILE_STATUS) == GL_FALSE)
		{
			int len = glGetShaderi(vertexShader, GL_INFO_LOG_LENGTH);
			System.out.println("ERROR: Shader compilation");
			System.out.println(glGetShaderInfoLog(vertexShader, len));
			System.exit(1);
		}

		fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(fragmentShader, fragmentSrc);
		glCompileShader(fragmentShader);

		if(glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE)
		{
			int len = glGetShaderi(fragmentShader, GL_INFO_LOG_LENGTH);
			System.out.println("ERROR: Shader compilation");
			System.out.println(glGetShaderInfoLog(fragmentShader, len));
			System.exit(1);
		}

		program = glCreateProgram();
		glAttachShader(program, vertexShader);
		glAttachShader(program, fragmentShader);
		glLinkProgram(program);

		if(glGetProgrami(program, GL_LINK_STATUS) == GL_FALSE)
		{
			int len = glGetProgrami(program, GL_INFO_LOG_LENGTH);
			System.out.println("Error program");
			System.out.println(glGetProgramInfoLog(program, len));
			System.exit(1);
		}
	}

	public void initFromFile(String vertexSrc, String fragmentSrc)
	{
		String vertex = "";
		String fragment = "";
		try
		{
			String buf;
			BufferedReader br = new BufferedReader(new FileReader(new File(vertexSrc)));
			while((buf = br.readLine()) != null)
				vertex += buf + "\n";
			br.close();
			br = new BufferedReader(new FileReader(new File(fragmentSrc)));
			while((buf = br.readLine()) != null)
				fragment += buf + "\n";
			br.close();
			init(vertex, fragment);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void start()
	{
		glUseProgram(program);
	}

	public void stop()
	{
		glUseProgram(0);
	}
	public int getProgram()
	{
		return program;
	}

	public void loadUniform(String name)
	{
		uniforms.put(name, glGetUniformLocation(program, name));
	}

	public void setUniformf1(String name, float value)
	{
		glUniform1f(uniforms.get(name), value);
	}

	public void destroy()
	{
		glDetachShader(program, vertexShader);
		glDetachShader(program, fragmentShader);
		glDeleteShader(vertexShader);
		glDeleteShader(fragmentShader);
		glDeleteProgram(program);
	}
}
