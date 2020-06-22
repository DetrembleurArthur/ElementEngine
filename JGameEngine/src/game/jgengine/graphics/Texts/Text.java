package game.jgengine.graphics.texts;

import game.jgengine.debug.Logs;
import game.jgengine.graphics.Mesh;
import game.jgengine.graphics.shapes.BoundingBox;
import game.jgengine.graphics.shapes.Shape;
import jdk.jshell.spi.ExecutionControl;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector2f;
import org.joml.Vector4f;

import java.util.ArrayList;

public class Text extends Shape
{
	private Font font;
	private String text;
	private float sizePx;
	private ArrayList<BoundingBox> charBoundingBoxes;

	public Text(@NotNull Font font, String text)
	{
		super(null, font.getTextureAtlas());
		charBoundingBoxes = new ArrayList<>();
		setFont(font);
		this.text = text;
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

	public void setFont(@NotNull Font font)
	{
		this.font = font;
		setTexture(font.getTextureAtlas());
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text.replace("\t", "    ");
		setMesh(font.generateMesh(text, sizePx / font.getSizePx()));
		loadBoundingBoxes();
	}

	private void loadBoundingBoxes()
	{
		var len = text.length();
		charBoundingBoxes.clear();
		for(int i = 0; i < len; i++)
		{
			charBoundingBoxes.add(getCharBox(i));
		}
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

	public Vector2f getSize()
	{
		return new Vector2f();
	}

	@Override
	public BoundingBox getBoundingBox()
	{
		BoundingBox globalBox = (BoundingBox) getCharBox(0).clone();
		float maxX = globalBox.getX();
		for(int i = 1; i < text.length(); i++)
		{
			var box = getCharBox(i);
			if(globalBox.getY() > box.getY()) globalBox.setY(box.getY());
			if(maxX < box.getX()) maxX = box.getX() + box.getWidth();

			if(globalBox.getHeight() < box.getHeight()) globalBox.setHeight(box.getHeight());
		}
		globalBox.setWidth(maxX - globalBox.getX());
		return globalBox;
	}

	@Override
	protected void setVerticesOrigin(float x, float y)
	{

	}
}
