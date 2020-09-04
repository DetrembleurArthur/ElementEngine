package game.jgengine.graphics.texts;

import game.jgengine.debug.Logs;
import game.jgengine.graphics.vertex.Mesh;
import game.jgengine.graphics.shapes.BoundingBox;
import game.jgengine.graphics.shapes.Shape;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;

public class Text extends Shape
{
	private Font font;
	private String text;
	private float ratio;

	public Text(@NotNull Font font, String text)
	{
		super(null, font.getTextureAtlas());
		initFont(font);
		setText(text);
	}

	public Font getFont()
	{
		return font;
	}

	private void initFont(@NotNull Font font)
	{
		this.font = font;
		setTexture(font.getTextureAtlas());
	}

	public void setFont(@NotNull Font font)
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
		Logs.print(pos.x);
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

}
