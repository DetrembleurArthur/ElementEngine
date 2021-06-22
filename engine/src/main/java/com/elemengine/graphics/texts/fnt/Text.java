package com.elemengine.graphics.texts.fnt;

import com.elemengine.components.event.Valuable;
import com.elemengine.graphics.shapes.BoundingBox;
import com.elemengine.graphics.vertex.Mesh;
import com.elemengine.debug.Log;
import com.elemengine.entity.GameObject;
import org.joml.Vector2f;

public class Text extends GameObject implements Valuable
{
	private Font font;
	private String text;
	private float ratio;

	public Text(Font font, String text)
	{
		super(null, font.getTextureAtlas());
		initFont(font);
		setText(text);
	}

	public Font getFont()
	{
		return font;
	}

	private void initFont(Font font)
	{
		this.font = font;
		setTexture(font.getTextureAtlas());
	}

	public void setFont(Font font)
	{
		initFont(font);
		setText();
	}

	public String getText()
	{
		return text;
	}

	public void setText()
	{
		setText(text);
	}

	public void setText(String text)
	{
		this.text = text.replace("\t", "    ");
		generate();
	}

	private void generate()
	{
		var info = font.generateMesh(text);
		ratio = info.heightRatio;
		setMesh(info.mesh);
	}

	public void setTextWidth(float width)
	{
		setSize(width, width * ratio);
	}

	public void setTextHeight(float height)
	{
		setSize(height / ratio, height);
	}


	public void normalize()
	{
		setTextHeight(getHeight());
	}

	public BoundingBox getCharBox(int i)
	{
		Mesh mesh = getMesh();
		var pos = getPosition2D();
		var size = getSize2D();
		var topLeft = mesh.getPosition(i * 4);
		var bottomRight = mesh.getPosition(i * 4 + 2);
		Log.print(pos.x);
		return new BoundingBox(
				topLeft.x * size.x + pos.x,
				topLeft.y * size.y + pos.y,
				(bottomRight.x - topLeft.x) * size.x,
				(bottomRight.y - topLeft.y) * size.y);
	}


	public boolean complexCollide(Vector2f pos)
	{
		String text = this.text.replace("\n", "");
		for(int i = 0; i < text.length(); i++)
		{
			if(getCharBox(i).isCollision(pos))
				return true;
		}
		return false;
	}

	@Override
	public Object getValue()
	{
		return getText();
	}

	@Override
	public void setValue(Object value)
	{
		setText((String) value);
	}
}
