#version 330 core

in vec4 fColor;

out vec4 color;

uniform float number;

void main()
{
    color = vec4(fColor.xyz, 1);
}
