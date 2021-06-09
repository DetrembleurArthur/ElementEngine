#version 330 core
layout (location=0) in vec3 aPos;
layout (location=1) in vec2 aUV;

uniform mat4 uProjection;
uniform mat4 uView;
uniform mat4 uModel;
uniform vec2 lightPosition;

uniform vec2 screenSize;
uniform vec2 aspectPosition;

out vec2 olightPosition;
out vec2 fUV;

void main()
{
    fUV = aUV;
    gl_Position = uProjection * uView * uModel * vec4(aPos, 1.0);
    vec4 clipSpace = uProjection * uView * vec4(lightPosition, 0, 1);
    vec3 ndc = clipSpace.xyz / clipSpace.w;
    olightPosition = (ndc.xy * 0.5 + 0.5) * screenSize + aspectPosition;
}
