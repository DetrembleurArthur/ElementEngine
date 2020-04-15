#version 330 core

layout (location=0) in vec3 aPos;
layout (location=1) in vec4 aColor;

uniform float x;
uniform float y;

out vec4 fColor;

void main()
{
    fColor = aColor;
    gl_Position = vec4(aPos.xyz, 1) + vec4(x, y, 0, 0);
}
