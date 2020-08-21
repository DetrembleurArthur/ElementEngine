package game.jgengine.graphics.texts;

import game.jgengine.graphics.shapes.Shape;

public class PrimitiveText extends Shape
{
	private String text;
	private PrimitiveFont font;

	public PrimitiveText(String text, PrimitiveFont font)
	{
		super(null, null);
		this.text = text;
		this.font = font;
		generate();
	}

	private void generate()
	{
		setMesh(font.generateMesh(text));
	}

	public String getText()
	{
		return text;
	}

	public PrimitiveFont getFont()
	{
		return font;
	}

	@Override
	protected void setVerticesOrigin(float x, float y)
	{

	}
}
