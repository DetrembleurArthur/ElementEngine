#version 460 core
uniform vec4 fColor;
in vec2 fUV;
out vec4 color;

void main()
{
    color = fColor;
}
