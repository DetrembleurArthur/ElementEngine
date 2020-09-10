#version 330 core

in vec2 fUV;
out vec4 color;

uniform vec4 uFillColor;
uniform sampler2D TEX_SAMPLER;
uniform int isTextured;

//uniform vec2 lightPosition;
uniform vec4 lightColor;
uniform float lightRadius;
uniform vec4 basicPower;
uniform float lightFade;

in vec2 olightPosition;


void lighting()
{
    float distance = length(olightPosition - gl_FragCoord.xy);
    float attenuation = lightFade - distance / lightRadius;
    vec4 fact;
    for(int i = 0; i < 4; i++)
    {
        fact[i] = basicPower[i] + attenuation;
        if(fact[i] < basicPower[i]) fact[i] = basicPower[i];
        else if(fact[i] > 1) fact[i] = 1;
    }
    color = color * fact * lightColor;
    if(color.x > 1) color.x = 1;
    if(color.y > 1) color.y = 1;
    if(color.z > 1) color.z = 1;
    if(color.w > 1) color.w = 1;
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
    lighting();
}
