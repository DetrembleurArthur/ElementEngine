package game.jgengine.graphics.text;

import game.jgengine.graphics.shaders.Texture;
import game.jgengine.graphics.shapes.Rectangle;
import game.jgengine.graphics.shapes.Shape;
import org.jetbrains.annotations.NotNull;

public class Text extends Shape
{
	private Font font;
	private String text;

	public Text(@NotNull Font font, String text)
	{
		super(null, font.getGlyphsTexture());
		setFont(font);
		setText(text);
	}

	public Font getFont()
	{
		return font;
	}

	public void setFont(@NotNull Font font)
	{
		this.font = font;
		setTexture(font.getGlyphsTexture());
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
		setMesh(font.generateMesh(text));
	}

	@Override
	protected void setVerticesOrigin(float x, float y)
	{

	}
}
