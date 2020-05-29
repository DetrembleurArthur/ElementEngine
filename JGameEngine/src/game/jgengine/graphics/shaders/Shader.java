package game.jgengine.graphics.shaders;

import game.jgengine.graphics.camera.Camera3D;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryUtil;

import java.io.*;
import java.nio.FloatBuffer;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.system.MemoryUtil.memFree;

public class Shader
{
	public static final Shader DEFAULT = new Shader(
			"src/game/jgengine/graphics/shaders/vertex.glsl",
			"src/game/jgengine/graphics/shaders/fragment.glsl",
			true
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
		loadUniforms();
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
			{
				var tokens = buf.split(" ");
				if(tokens.length >= 3)
				{
					if(tokens[0].equals("uniform"))
					{
						var variable = tokens[2].split(";");
						addUniform(variable[0]);
					}
				}
				vertex += buf + "\n";
			}
			br.close();
			br = new BufferedReader(new FileReader(new File(fragmentSrc)));
			while((buf = br.readLine()) != null)
			{
				var tokens = buf.split(" ");
				if(tokens.length >= 3)
				{
					if(tokens[0].equals("uniform"))
					{
						var variable = tokens[2].split(";");
						addUniform(variable[0]);
					}
				}
				fragment += buf + "\n";
			}
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
	public void start(Camera3D camera3D)
	{
		glUseProgram(program);
		uploadMat4f("uProjection", camera3D.getProjectionMatrix());
		uploadMat4f("uView", camera3D.getViewMatrix());
	}

	public void stop()
	{
		glUseProgram(0);
	}
	public int getProgram()
	{
		return program;
	}

	private void addUniform(String name)
	{
		uniforms.put(name, -1);
	}

	private void loadUniforms()
	{
		for(String key : uniforms.keySet())
		{
			uniforms.put(key, glGetUniformLocation(program, key));
		}
	}

	public void setUniformf1(String name, float value)
	{
		glUniform1f(uniforms.get(name), value);
	}

	public void setUniformf4(String name, Vector4f value)
	{
		glUniform4f(uniforms.get(name), value.x, value.y, value.z, value.w);
	}

	public void setUniform1i(String name, int value)
	{
		glUniform1i(uniforms.get(name), value);
	}

	public void destroy()
	{
		glDetachShader(program, vertexShader);
		glDetachShader(program, fragmentShader);
		glDeleteShader(vertexShader);
		glDeleteShader(fragmentShader);
		glDeleteProgram(program);
	}

	public void uploadMat4f(String name, Matrix4f matrix)
	{
		FloatBuffer buffer = MemoryUtil.memAllocFloat(16); //4 x 4
		matrix.get(buffer);
		glUniformMatrix4fv(uniforms.get(name), false, buffer);
		if(buffer != null)
		{
			memFree(buffer);

		}
	}

	public void uploadTexture(String name, int slot)
	{
		var variable = uniforms.get(name);
		if(variable != null)
			glUniform1i(variable, slot);
	}
}
