package com.elemengine.graphics.texts.ttf;

import com.elemengine.debug.Log;
import com.elemengine.entity.GameObject;
import com.elemengine.graphics.rendering.Texture;
import com.elemengine.graphics.vertex.Mesh;
import com.elemengine.utils.StringUtil;
import org.joml.Vector2f;

public class TTFText extends GameObject
{
    private TTFont font;
    private String textUnicode;

    public TTFText(TTFont font)
    {
        super(null, font.getTexture());
        this.font = font;
    }

    private void generateCharacter(CharInfo charInfo, float[] positions, float[] uvs, int[] indexes, int i, Vector2f pos)
    {
        float x0 = pos.x;
        float y0 = pos.y;
        float x1 = x0 + charInfo.width;
        float y1 = y0 + charInfo.height;

        float ux0 = charInfo.textureCoordinates[0].x;
        float uy0 = charInfo.textureCoordinates[0].y;
        float ux1 = charInfo.textureCoordinates[1].x;
        float uy1 = charInfo.textureCoordinates[1].y;

        positions[i * 2 * 4] = x0;
        positions[i * 2 * 4 + 1] = y0;
        uvs[i * 2 * 4] = ux0;
        uvs[i * 2 * 4 + 1] = uy0;

        positions[i * 2 * 4 + 2] = x0;
        positions[i * 2 * 4 + 3] = y1;
        uvs[i * 2 * 4 + 2] = ux0;
        uvs[i * 2 * 4 + 3] = uy1;

        positions[i * 2 * 4 + 4] = x1;
        positions[i * 2 * 4 + 5] = y1;
        uvs[i * 2 * 4 + 4] = ux1;
        uvs[i * 2 * 4 + 5] = uy1;

        positions[i * 2 * 4 + 6] = x1;
        positions[i * 2 * 4 + 7] = y0;
        uvs[i * 2 * 4 + 6] = ux1;
        uvs[i * 2 * 4 + 7] = uy0;

        indexes[i * 6] = i * 4;
        indexes[i * 6 + 1] = i * 4 + 1;
        indexes[i * 6 + 2] = i * 4 + 2;
        indexes[i * 6 + 3] = i * 4;
        indexes[i * 6 + 4] = i * 4 + 2;
        indexes[i * 6 + 5] = i * 4 + 3;
    }

    //faire les retours à la ligne
    private void generateText(String text)
    {
        Vector2f pos = new Vector2f();
        text = text.replaceAll("\t", "   ");
        int len = text.length();
        float[] positions = new float[2 * 4 * len]; //2 positions pour chacun des 4 points d'un caractère
        float[] uvs = new float[2 * 4 * len];
        int[] indexes = new int[6 * len];
        for(int i = 0; i < text.length(); i++)
        {
            CharInfo charInfo = font.getCharacter(text.charAt(i));
            if(text.charAt(i) == '\n')
            {
                pos.y += font.getLineHeight();
                pos.x = 0;
                continue;
            }
            if(charInfo.width == 0)
            {
                Log.print("Unknown character...");
                continue;
            }
            generateCharacter(charInfo, positions, uvs, indexes, i, pos);
            pos.x += charInfo.width;
        }

        setMesh(new Mesh(positions, uvs, indexes, Mesh.DIMENSION_2, Mesh.TEXTURED));
    }

    public void setText(String text)
    {
        generateText(text);
        this.textUnicode = text;
    }

    public TTFont getFont()
    {
        return font;
    }

    public void setFont(TTFont font)
    {
        this.font = font;
    }

    public String getTextUnicode()
    {
        return textUnicode;
    }

    public void setTextUnicode(String textUnicode)
    {
        this.textUnicode = textUnicode;
    }
}
