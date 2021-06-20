package com.elemengine.graphics.shaders;

import com.elemengine.conf.Configuration;
import com.elemengine.graphics.camera.Camera3D;
import com.elemengine.utils.FileUtil;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.system.MemoryUtil.memFree;

public class Shader
{
    public static final Shader DEFAULT = new Shader(
            "shaders/vertex.glsl",
            "shaders/fragment.glsl",
            true
    );

    public static final Shader LIGHT = new Shader(
            "shaders/light/vertex.glsl",
            "shaders/light/fragment.glsl",
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
        if (areFile)
        {
            initFromFile(vertexSrc, fragmentSrc);
        } else
        {
            init(vertexSrc, fragmentSrc);
        }
    }

    public void init(String vertexSrc, String fragmentSrc)
    {
        vertexShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShader, vertexSrc);
        glCompileShader(vertexShader);

        if (glGetShaderi(vertexShader, GL_COMPILE_STATUS) == GL_FALSE)
        {
            int len = glGetShaderi(vertexShader, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: Shader compilation");
            System.out.println(glGetShaderInfoLog(vertexShader, len));
            System.exit(1);
        }

        fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShader, fragmentSrc);
        glCompileShader(fragmentShader);

        if (glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE)
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

        if (glGetProgrami(program, GL_LINK_STATUS) == GL_FALSE)
        {
            int len = glGetProgrami(program, GL_INFO_LOG_LENGTH);
            System.out.println("Error program");
            System.out.println(glGetProgramInfoLog(program, len));
            System.exit(1);
        }
        loadUniforms();
    }

    private String setUniformsFromFile(String filename) throws IOException
    {
        String masterBuf = "";
        String buf;
        BufferedReader br = new BufferedReader(new FileReader(FileUtil.getFile(filename)));
        while ((buf = br.readLine()) != null)
        {
            var tokens = buf.split(" ");
            if (tokens.length >= 3)
            {
                if (tokens[0].equals("uniform"))
                {
                    var variable = tokens[2].split(";");
                    addUniform(variable[0]);
                }
            }
            masterBuf += buf + "\n";
        }
        br.close();
        return masterBuf;
    }

    public void initFromFile(String vertexSrc, String fragmentSrc)
    {
        String vertex;
        String fragment;
        try
        {
            vertex = setUniformsFromFile(vertexSrc);
            fragment = setUniformsFromFile(fragmentSrc);
            init(vertex, fragment);
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
        for (String key : uniforms.keySet())
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
        memFree(buffer);
    }

    public void uploadTexture(String name, int slot)
    {
        var variable = uniforms.get(name);
        if (variable != null)
            glUniform1i(variable, slot);
    }

    public void setUniformf2(String name, Vector2f dimension)
    {
        glUniform2f(uniforms.get(name), dimension.x, dimension.y);
    }

    public void setUniform2fv(String name, float[] array)
    {
        glUniform2fv(uniforms.get(name), array);
    }

    public void setUniform4fv(String name, float[] array)
    {
        glUniform4fv(uniforms.get(name), array);
    }

    public void setUniform1fv(String name, float[] array)
    {
        glUniform1fv(uniforms.get(name), array);
    }
}
