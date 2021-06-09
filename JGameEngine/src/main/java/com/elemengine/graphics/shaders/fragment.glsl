#version 330 core

in vec2 fUV;
out vec4 color;

uniform vec4 uFillColor;
uniform sampler2D TEX_SAMPLER;
uniform int isTextured;
uniform float time;


void invertColor()
{
    color = vec4(vec3(1) - color.xyz, color.w);
}


void main()
{
    if(isTextured == 1)
    {
        color = texture(TEX_SAMPLER, fUV) * uFillColor;
    }
    else
    {
        color = uFillColor;
    }
}
