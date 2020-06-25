package game.jgengine.graphics.texts;

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
	private float sizePx;

	public Text(@NotNull Font font, String text)
	{
		super(null, font.getTextureAtlas());
		this.text = text;
		initFont(font);
		setSizePx(font.getSizePx());
	}

	public float getSizePx()
	{
		return sizePx;
	}

	public void setSizePx(float sizePx)
	{
		this.sizePx = sizePx;
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
		generate(new Vector2f());
	}

	private void generate(Vector2f origin)
	{
		setMesh(font.generateMesh(text, sizePx / font.getSizePx(), origin));
	}

	public BoundingBox getCharBox(int i)
	{
		Mesh mesh = getMesh();
		var pos = getPosition2D();
		var size = getScale();
		var topLeft = mesh.getPosition(i * 4);
		var bottomRight = mesh.getPosition(i * 4 + 2);
		return new BoundingBox(
				topLeft.x + pos.x,
				topLeft.y + pos.y,
				(bottomRight.x - topLeft.x) * size.x,
				(bottomRight.y - topLeft.y) * size.y);
	}

	@Override
	public Vector2f getSize()
	{
		var box = getScaledBoundingBox();
		return new Vector2f(box.getWidth(), box.getHeight());
	}

	@Override
	public void setSize(Vector2f size)
	{
		var box = getBoundingBox();
		setScale(new Vector3f(size.x / box.getWidth(), size.y / box.getHeight(), getScale().z));
	}

	@Override
	protected void setVerticesOrigin(float x, float y)
	{
		generate(new Vector2f(-x, -y));
	}

	@Override
	public void setTopRightOrigin()
	{
		var size = getSize();
		setOrigin(size.x, 0);
	}

	@Override
	public void setBottomLeftOrigin()
	{
		var size = getSize();
		setOrigin(0, size.y);
	}

	@Override
	public void setBottomRightOrigin()
	{
		var size = getSize();
		setOrigin(size);
	}

	@Override
	public void setCenterOrigin()
	{
		var size = getSize();
		setOrigin(size.x / 2, size.y / 2);
	}

	@Override
	public BoundingBox getBoundingBox()
	{
		String text = this.text.replace("\n", "");
		BoundingBox globalBox = (BoundingBox) getCharBox(0).clone();
		float width = globalBox.getX() + globalBox.getWidth();
		float height = globalBox.getY() + globalBox.getHeight();
		for(int i = 1; i < text.length(); i++)
		{
			BoundingBox box = getCharBox(i);
			if(globalBox.getX() > box.getX()) globalBox.setX(box.getX());
			if(globalBox.getY() > box.getY()) globalBox.setY(box.getY());
			if(width < box.getX() + box.getWidth()) width = box.getX() + box.getWidth();
			if(height < box.getY() + box.getHeight()) height = box.getY() + box.getHeight();
		}
		globalBox.setWidth(width - globalBox.getX());
		globalBox.setHeight(height - globalBox.getY());
		return globalBox;
	}

	public BoundingBox getScaledBoundingBox()
	{
		BoundingBox box = getBoundingBox();
		box.setWidth(box.getWidth() * getScale().x);
		box.setHeight(box.getHeight() * getScale().y);
		return box;
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
